package com.example.customerconnect.service;

import com.example.customerconnect.dto.CustomerDto;
import com.example.customerconnect.dto.UserDTO;
import com.example.customerconnect.entity.CustomerEntity;
import com.example.customerconnect.mapper.CustomerMapper;
import com.example.customerconnect.repository.CustomerRepository;
import com.example.customerconnect.service.ex.CustomerAlreadyExistsException;
import com.example.customerconnect.service.ex.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final RestTemplate restTemplate;

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto, UserDTO userDTO) {
        Optional<CustomerEntity> optCustomer = customerRepository.findByUuid(userDTO.getUuid());

        if (optCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists");
        }

        CustomerEntity customerEntity = customerMapper.dtoToEntity(customerDto);
        customerEntity.setUuid(userDTO.getUuid());
        customerRepository.save(customerEntity);
        return customerMapper.entityToDto(customerEntity);
    }

    @Override
    public CustomerDto getCustomer(UserDTO userDTO) {
        Optional<CustomerEntity> optCustomer = customerRepository.findByUuid(userDTO.getUuid());

        if (optCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return customerMapper.entityToDto(optCustomer.get());
    }

    @Override
    public Set<CustomerDto> getAllCustomers() {
        Set<CustomerEntity> customers = new HashSet<>(customerRepository.findAll());

        return customers.stream()
                .map(customerMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public CustomerDto customerUpdateDetails(UserDTO userDTO, CustomerDto customerDto) {
        Optional<CustomerEntity> optCustomer = customerRepository.findByUuid(userDTO.getUuid());

        if (optCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found");
        }

        CustomerEntity customerEntity = optCustomer.get();

        customerEntity.setFirstName(customerDto.getFirstName());
        customerEntity.setLastName(customerDto.getLastName());
        customerRepository.save(customerEntity);
        return customerMapper.entityToDto(customerEntity);
    }

    @Override
    public void deleteCustomer(UserDTO userDTO) {
        Optional<CustomerEntity> optCustomer = customerRepository.findByUuid(userDTO.getUuid());

        if (optCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found");
        }

        CustomerEntity customerEntity = optCustomer.get();
        String couponServiceUrl = "http://coupon-hub/api/coupons/deleteCustomerCoupon/" + customerEntity.getUuid();
        restTemplate.exchange(couponServiceUrl, HttpMethod.DELETE, new HttpEntity<>(customerEntity.getUuid()), UUID.class);

        customerRepository.delete(customerEntity);
    }

}
