package com.spring.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
