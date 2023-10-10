package com.address.service.impl;

import com.address.entity.Address;
import com.address.entity.AddressResponse;
import com.address.repository.AddressRepository;
import com.address.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<AddressResponse> getAddress() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressResponse> addressResponses = addresses.stream().map(address -> modelMapper.map(address, AddressResponse.class)).collect(Collectors.toList());
        return addressResponses;
    }

    @Override
    public AddressResponse getAddressByEmployeeId(Long employeeId) {
        Address address = addressRepository.findAddressByEmployeeId(employeeId);
        return modelMapper.map(address, AddressResponse.class);
    }
}
