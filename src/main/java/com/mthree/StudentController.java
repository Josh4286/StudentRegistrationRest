package com.mthree;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/api/student")
public class StudentController {
	private final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping(path= "{studentId}")
	public Student getStudentById(@PathVariable("studentId") int id){
		return studentService.getStudentById(id);
	}

	@GetMapping
	public List<Student> getStudents(){
		return studentService.getStudents();
	}
	
	@PostMapping
	public void addStudent(@RequestBody Student student) {
		studentService.addStudent(student);
	}
	
	@DeleteMapping(path="{studentId}")
	public void deleteStudentById(@PathVariable("studentId") int id) {
		studentService.deleteStudentById(id);
	}
	
	@PutMapping(path="{studentId}")
	public void updateStudentById(
			@PathVariable("studentId") int id,
			@RequestBody Student student) {
		studentService.updateStudentById(id, student);
	}
}
