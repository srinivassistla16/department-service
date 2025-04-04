package com.microserrvices.departmentservice.client;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import com.microserrvices.departmentservice.dto.Employee;


@HttpExchange
public interface EmployeeClient {

	@GetExchange("/emp/employee/{deptIdentifier}")
	public List<Employee> getEmployeesByDeptIdentifier(@PathVariable String deptIdentifier);
}
