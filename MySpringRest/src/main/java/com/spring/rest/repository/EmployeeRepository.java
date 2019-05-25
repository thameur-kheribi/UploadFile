package com.spring.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
