package com.spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.model.Student;
import com.spring.rest.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

    // GET  /Students  Gets all students
	@RequestMapping("/Students")
	public List<Student> getAll() {
		return studentService.getAllStudents();
	}
	
	// GET  /Students/id  Get student by id
	@RequestMapping(value = "/Students/{id}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable String id)
	{
		return studentService.getStudent(Long.valueOf(id));
	}
	
	
	// POST /Students Add student
	@RequestMapping(value = "/Students", method = RequestMethod.POST)
	public void addStudent(@RequestBody Student student)
	{
		studentService.addStudent(student);
	}
	
	// PUT /Students/id Update student
	@RequestMapping(value = "/Students/{id}", method = RequestMethod.PUT)
	public void updateStudent(@PathVariable String id, 
			                  @RequestBody Student student)
	{
		studentService.updateStudent(Long.valueOf(id), student);
	}
	
	@RequestMapping(value = "/Students/{id}", method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable String id)
	{
		studentService.deleteStudent(Long.valueOf(id));
	}
//	@RequestMapping("/Students/accounts")
//	public String getAccount(@RequestParam String id)
//	{
//		return "TEST OK";
//	}

}
