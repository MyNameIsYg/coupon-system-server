package com.example.customerconnect.service;

import com.example.customerconnect.dto.CustomerDto;
import com.example.customerconnect.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CustomerService {
    CustomerDto addCustomer(CustomerDto customerDto, UserDTO userDTO);

    CustomerDto getCustomer(UserDTO userDTO);

    Set<CustomerDto> getAllCustomers();

    CustomerDto customerUpdateDetails(UserDTO userDTO, CustomerDto customerDto);

    void deleteCustomer(UserDTO userDTO);


}
