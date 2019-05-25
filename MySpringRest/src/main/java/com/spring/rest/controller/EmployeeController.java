package com.spring.rest.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.model.Employee;
import com.spring.rest.model.Student;
import com.spring.rest.repository.EmployeeRepository;
import com.spring.rest.service.StorageService;

@RestController

@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	StorageService storageService;

    // GET  /Employees  Gets all employees
	@CrossOrigin(origins = "*")
	@RequestMapping("/Employees")
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}
	
	// GET  /Employees/id  Get employee by id
	
	@RequestMapping(value = "/Employees/{id}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable String id)
	{
		return employeeRepository.findById(Long.valueOf(id)).get();
	}
	
	
	// POST /Employees Add employee
	@RequestMapping(value = "/Employees", method = RequestMethod.POST)
	public boolean addStudent(@RequestBody Employee employee)
	{
		for (Employee element : employeeRepository.findAll()) 
		{
			if (element.getFullName().equals(employee.getFullName()))
			{
				return false;
			}
		}
		employeeRepository.save(employee);
		return true;
	}
	
	// PUT /Employees/id Update employee
	@RequestMapping(value = "/Employees/{id}", method = RequestMethod.PUT)
	public void updateStudent(@PathVariable String id, 
			                  @RequestBody Employee employee)
	{
		
		for (Employee element : employeeRepository.findAll()) {
			if (element.getEmployeeID().equals(Long.valueOf(id)) && element.getEmployeeID().equals(Long.valueOf(id)))
			{
				employeeRepository.save(employee);
				return;
			}
		}
	}
    
	
	// DELETE /Employees/id Delete employee	
	@RequestMapping(value = "/Employees/{id}", method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable String id)
	{
		employeeRepository.deleteById(Long.valueOf(id));
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/UploadFile", method = RequestMethod.POST)
	public boolean uploadFile(@RequestParam("file") MultipartFile file,
			                  @RequestParam("employee") String employee) throws IOException
	{
		boolean result = false;
		// DESERIALIZE Request param
		Employee emp = new ObjectMapper().readValue(employee, Employee.class);
		// SAVE Employee object
		emp.setFileName(file.getOriginalFilename());
		employeeRepository.save(emp);
		// STORE the File
		storageService.store(file);
		result = true;
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
