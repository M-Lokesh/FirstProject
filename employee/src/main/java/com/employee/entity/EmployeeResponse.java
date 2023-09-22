package com.employee.entity;

import lombok.Data;

@Data
public class EmployeeResponse {

    private Long id;
    private String name;
    private String email;
    private String role;
    public AddressResponse addressResponse;
}
