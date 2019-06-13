package com.spring.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

}
