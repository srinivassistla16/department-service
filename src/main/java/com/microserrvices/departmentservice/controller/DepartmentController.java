package com.microserrvices.departmentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microserrvices.departmentservice.client.EmployeeClient;
import com.microserrvices.departmentservice.dto.DepartmentWithEmployees;
import com.microserrvices.departmentservice.dto.Employee;
import com.microserrvices.departmentservice.entity.Department;
import com.microserrvices.departmentservice.service.DepartmentService;
import com.microserrvices.departmentservice.service.DepartmentServiceImpl;

@RestController
@RequestMapping("/dept")
public class DepartmentController {
	
	@Autowired
	private DepartmentServiceImpl departmentService;
	
	@Autowired
	private EmployeeClient employeeClient;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/department/{depattmentIdentifier}")
	public ResponseEntity<Department> getDepartmentByIdentifier(@PathVariable String depattmentIdentifier) {
		return ResponseEntity.ok().body(departmentService.getDepartmentByIdentifier(depattmentIdentifier));
		
	}

	@GetMapping("/departments")
	public ResponseEntity<List<Department>> getAllDepartments() {
		return ResponseEntity.ok().body(departmentService.getAllDepts());
	}

	
	@PostMapping("/newDepartment")
	public ResponseEntity<Department> saveDeaprtment(@RequestBody Department department) {
		Department dept = departmentService.addNewDeartment(department);
		return ResponseEntity.ok(dept);
	}
	
	@DeleteMapping("/department/{departmentIdentifier}")
	public ResponseEntity<Department> deleteDepartmentByIdentier(@PathVariable String departmentIdentifier) {
		return ResponseEntity.ok().body(departmentService.deleteDepartmentByIdentier(departmentIdentifier));
	}
	
	@GetMapping("/departments/employees-web-client/{identifier}")
	public List<Employee> getEmployeesWebClient(@PathVariable String identifier) {
		return employeeClient.getEmployeesByDeptIdentifier(identifier);	}
	
	@GetMapping("/departments/employees-rest-template/{identifier}")
	public List<Employee> getEmployeesRestTemplate(@PathVariable String identifier) {
		return restTemplate.getForObject("http://EMPLOYEE-SERVICE:8082/emp/employee/"+ identifier, List.class);
	}                                     
	
	@GetMapping("/department-with-employees-rest-template/{identifier}")
	public DepartmentWithEmployees getFullDeptInfoRestTemplate(@PathVariable String identifier) {
		DepartmentWithEmployees departmentWithEmployees = new DepartmentWithEmployees();
		Department dept = departmentService.getDepartmentByIdentifier(identifier);
		List<Employee> employees = restTemplate.getForObject("http://EMPLOYEE-SERVICE:8082/emp/employee/"+ identifier, List.class);
		departmentWithEmployees.setDept(dept);
		departmentWithEmployees.setEmployees(employees);
		return departmentWithEmployees;
	}
	@GetMapping("/department-with-employees-web-client/{identifier}")
	public DepartmentWithEmployees getFullDeptInfoWebClient(@PathVariable String identifier) {
		DepartmentWithEmployees departmentWithEmployees = new DepartmentWithEmployees();
		Department dept = departmentService.getDepartmentByIdentifier(identifier);
		List<Employee> employees = employeeClient.getEmployeesByDeptIdentifier(identifier);
		departmentWithEmployees.setDept(dept);
		departmentWithEmployees.setEmployees(employees);
		return departmentWithEmployees;
	}

}
