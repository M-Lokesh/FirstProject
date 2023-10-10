package com.employee.service.impl;

import com.employee.entity.AddressResponse;
import com.employee.entity.Employee;
import com.employee.entity.EmployeeResponse;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    //@Autowired
    private RestTemplate restTemplate;

    @Value("${address-service.base.url}")
    String baseUrl;

    /**
     * Constructor injection
     *
     * @param baseUrl
     * @param restTemplateBuilder
     */
    public EmployeeServiceImpl(@Value("${address-service.base.url}") String baseUrl, RestTemplateBuilder restTemplateBuilder) {
        System.out.println("uri "+baseUrl);
        this.restTemplate = restTemplateBuilder
                .rootUri(baseUrl)
                            .build();
    }

    @Override
    public List<EmployeeResponse> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses =  employees.stream().map(employee -> modelMapper.map(employee, EmployeeResponse.class)).collect(Collectors.toList());
        return employeeResponses;
    }

    @Override
    public EmployeeResponse getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        AddressResponse addressResponse = restTemplate.getForObject(  "/{employeeId}", AddressResponse.class, employeeId);
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }
}
