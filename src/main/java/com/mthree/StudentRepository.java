package com.mthree;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	//Will transform into SELECT * FROM student WHERE email = ?
	//or @Query("SELECT s FROM Student s WHERE s.name = ?1") this is jpql not sql
	Optional<Student> findStudentByName(String name);
}
