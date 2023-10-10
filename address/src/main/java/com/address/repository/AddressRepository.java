package com.address.repository;

import com.address.entity.Address;
import com.address.entity.AddressResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "select a.id, a.street ,a.city, a.pin_code from public.address a join public.employee e on e.id = a.employee_id", nativeQuery = true)
    List<Address> findAllAddressByItsEmployees();

    @Query(value = "select a.id, a.street ,a.city, a.pin_code from public.address a join public.employee e on e.id = a.employee_id where a.id = :id", nativeQuery = true)
    Address findAddressByEmployeeId(Long id);
}
