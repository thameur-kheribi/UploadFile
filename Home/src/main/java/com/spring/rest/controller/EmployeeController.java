package com.spring.rest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

//import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.model.Employee;
import com.spring.rest.model.Role;
import com.spring.rest.model.Student;
import com.spring.rest.model.User;
import com.spring.rest.repository.EmployeeRepository;
import com.spring.rest.repository.RoleRepository;
import com.spring.rest.repository.UserRepository;
import com.spring.rest.service.StorageService;

@RestController
@CrossOrigin(origins = "http://localhost:4201")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	StorageService storageService;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	// GET /Employees Gets all employees
	@CrossOrigin(origins = "*")
	@RequestMapping("/Employees")
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	@GetMapping("/Users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/Roles")
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}


	// GET /Employees/id Get employee by id

	@RequestMapping(value = "/Employees/{id}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable String id) {
		return employeeRepository.findById(Long.valueOf(id)).get();
	}

	// POST /Employees Add employee
	@RequestMapping(value = "/Employees", method = RequestMethod.POST)
	public boolean addStudent(@RequestBody Employee employee) {
		for (Employee element : employeeRepository.findAll()) {
			if (element.getFullName().equals(employee.getFullName())) {
				return false;
			}
		}
		employeeRepository.save(employee);
		return true;
	}

	// PUT /Employees/id Update employee
	@RequestMapping(value = "/Employees/{id}", method = RequestMethod.PUT)
	public void updateStudent(@PathVariable String id, @RequestBody Employee employee) {

		for (Employee element : employeeRepository.findAll()) {
			if (element.getEmployeeID().equals(Long.valueOf(id)) && element.getEmployeeID().equals(Long.valueOf(id))) {
				employeeRepository.save(employee);
				return;
			}
		}
	}

	// DELETE /Employees/id Delete employee
	@RequestMapping(value = "/Employees/{id}", method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable String id) {
		employeeRepository.deleteById(Long.valueOf(id));
	}

	@RequestMapping(value = "/UploadFile", method = RequestMethod.POST)
	public boolean uploadFile(@RequestParam(name = "file") Optional<MultipartFile> file,
			@RequestParam(name = "employee") String employee) throws IOException {

		boolean result = false;
		// DESERIALIZE Request param
		Employee emp = new ObjectMapper().readValue(employee, Employee.class);
		// SAVE Employee object
		// IF THE file param is passed to server
		if (file.isPresent()) {
			emp.setFileName(file.get().getOriginalFilename());
			storageService.store(file.get());
		}
		employeeRepository.save(emp);
		// STORE the File
		result = true;
		return result;
	}

	// GET Image
	@RequestMapping("/Files/{fileName}")
	public ResponseEntity<InputStreamResource> getFile(@PathVariable String fileName) throws IOException {
		Resource imgFile = storageService.LoadFile(fileName);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
				.body(new InputStreamResource(imgFile.getInputStream()));
	}

}
