package com.address.service;

import com.address.entity.AddressResponse;

import java.util.List;

public interface AddressService {

    List<AddressResponse> getAddress();

    AddressResponse getAddressByEmployeeId(Long employeeId);
}
