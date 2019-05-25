package com.spring.rest.service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.model.Student;
import com.spring.rest.repository.StudentRepository;

@Service
public class StudentService implements ServiceInterface {
	
	@Autowired
	StudentRepository studentRepository;
	@Override
	public List<Student> getAllStudents()
	{
		return studentRepository.findAll();
	}
	
	@Override
	public Student getStudent(Long id)
	{
		return studentRepository.findById(id).get();
	}
	
	@Override
	public void addStudent(Student student)
	{
		studentRepository.save(student);
	}
	
	@Override
	public void updateStudent(Long id, Student student)
	{
		
		for (Student element : getAllStudents()) {
			if (element.getId().equals(id) && student.getId().equals(id))
			{
				studentRepository.save(student);
				return;
			}
		}
		
	}
	
	@Override
	public void deleteStudent(Long id)
	{
		studentRepository.deleteById(id);
	}

}
