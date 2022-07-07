package com.mthree;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mthree.exceptions.UserAlreadyExistsException;
import com.mthree.exceptions.UserNotFoundException;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	private final AddressRepository addressRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository, AddressRepository addressRepository) {
		super();
		this.studentRepository = studentRepository;
		this.addressRepository = addressRepository;
	}


	public List<Student> getStudents(){
		return studentRepository.findAll();
	}


	public void addStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByName(student.getName());
		if (studentOptional.isPresent()) {
			throw new UserAlreadyExistsException("Student with the name " + student.getName() + " is already in database");
		}
		Student updatedStudent = checkIfAddressExists(student, student.getAddress());
//		Optional<Address> addressOptional = addressRepository.findAddressByStreet(student.getAddress().getStreet());
//		if (addressOptional.isPresent()) {
//			student.setAddress(addressOptional.get());
//		} else {
//			addressRepository.save(student.getAddress());
//		}
		studentRepository.save(updatedStudent);
		System.out.println(updatedStudent);
	}


	public void deleteStudentById(int id) {
		boolean exists = studentRepository.existsById(id);
		System.out.println(exists);
		if (!exists) {
			System.out.println("inside exists");
			throw new UserNotFoundException("Student with id " + id + " does not exist");
		}
		System.out.println("deleted");
		studentRepository.deleteById(id);
	}
	
	public Student checkIfAddressExists(Student student, Address requestingAddress) {
		List<Address> addresses = addressRepository.findListAddressByStreet(requestingAddress.getStreet());
		boolean existsInDatabase = false;
		for(Address addr : addresses) {
			if (addr.equals(requestingAddress)){
				existsInDatabase = true;
				student.setAddress(addr);
				return student;
			}
		}
		if (!existsInDatabase) {
			addressRepository.save(requestingAddress);
			student.setAddress(requestingAddress);
		}
		return student;
	}


@Transactional
	public void updateStudentById(int id, Student studentUpdate) {
	System.out.println(studentUpdate);
		Student student = studentRepository
				.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Student with id " + id + " does not exist"));
		String name = studentUpdate.getName();
		if (
				name != null && 
				name.length() > 0 && 
				!Objects.equals(student.getName(), name) && 
				studentRepository.findStudentByName(name).isEmpty()) {
			student.setName(name);
		}
		Student updatedStudent = checkIfAddressExists(student, studentUpdate.getAddress());
		studentRepository.save(updatedStudent);
	}


	public Student getStudentById(int id) {
		Optional<Student> studentOptional = studentRepository.findById(id);
		if (studentOptional.isPresent()) {
			return studentOptional.get();
		} else {
			new UserNotFoundException("Student with id " + id + " does not exist");
		}
		return null;
	}
	
}
