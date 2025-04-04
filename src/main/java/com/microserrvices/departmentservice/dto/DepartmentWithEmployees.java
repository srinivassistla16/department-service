package com.microserrvices.departmentservice.dto;

import java.util.List;

import com.microserrvices.departmentservice.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentWithEmployees {
	
	private Department dept;
	private List<Employee> employees;

}
