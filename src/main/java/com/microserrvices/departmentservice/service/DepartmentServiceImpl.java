package com.microserrvices.departmentservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microserrvices.departmentservice.entity.Department;
import com.microserrvices.departmentservice.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public Department getDepartmentByIdentifier(String deptIdentifier) {
		Department department = departmentRepository.findByDepartmentIdentifier(deptIdentifier);
			return department;
	}

	@Override
	public List<Department> getAllDepts() {
		List<Department>  departmentList = departmentRepository.findAll();
		return departmentList;
	}

	@Override
	public Department addNewDeartment(Department dept) {
		Department department= departmentRepository.save(dept);
		return department;
	}

	@Override
	public Department deleteDepartmentByIdentier(String departmentIdentifier) {
		Department deptToDelete = departmentRepository.findByDepartmentIdentifier(departmentIdentifier);
		departmentRepository.delete(deptToDelete);
		return deptToDelete;
		
	}

}
