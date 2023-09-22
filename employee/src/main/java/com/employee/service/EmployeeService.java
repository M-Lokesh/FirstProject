package com.employee.service;

import com.employee.entity.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getEmployees();
}
