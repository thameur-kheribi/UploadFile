package com.spring.rest.service;

import java.util.List;

import com.spring.rest.model.Student;

public interface ServiceInterface {
	List<Student> getAllStudents();
	Student getStudent(Long id);
	void addStudent(Student student);
	void updateStudent(Long id, Student student);
	void deleteStudent(Long id);
}
