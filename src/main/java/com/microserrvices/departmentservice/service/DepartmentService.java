package com.microserrvices.departmentservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microserrvices.departmentservice.entity.Department;


public interface DepartmentService {

	public abstract Department getDepartmentByIdentifier(String deptIdentifier);
	
	public abstract List<Department> getAllDepts();
	
	public abstract Department addNewDeartment(Department dept);
	
	public abstract Department deleteDepartmentByIdentier(String departmentIdentifier);


}
