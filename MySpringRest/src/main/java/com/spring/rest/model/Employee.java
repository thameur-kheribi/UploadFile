package com.spring.rest.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long EmployeeID;
	private String FullName;
	private String EMPCode;
	private String Mobile;
	private String Position;
	private String FileName;
	
	@JsonGetter("EmployeeID")
	public Long getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(Long employeeID) {
		EmployeeID = employeeID;
	}
	@JsonProperty("FullName")
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	@JsonProperty("EMPCode")
	public String getEMPCode() {
		return EMPCode;
	}
	public void setEMPCode(String eMPCode) {
		EMPCode = eMPCode;
	}
	@JsonProperty("Mobile")
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	@JsonProperty("Position")
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	
	@JsonGetter("FileName")
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
}
