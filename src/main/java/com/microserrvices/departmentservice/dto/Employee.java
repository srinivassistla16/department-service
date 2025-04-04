package com.microserrvices.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
	private Long id;
	private String firstName;
	private String lastName;
	private String deptIdentifier;

}
