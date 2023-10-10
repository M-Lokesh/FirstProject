package com.address.controller;

import com.address.entity.AddressResponse;
import com.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping()
    public ResponseEntity<List<AddressResponse>> getAddress() {
        return new ResponseEntity<>(addressService.getAddress(), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") Long employeeId) {
        return new ResponseEntity<>(addressService.getAddressByEmployeeId(employeeId), HttpStatus.OK);
    }
}
