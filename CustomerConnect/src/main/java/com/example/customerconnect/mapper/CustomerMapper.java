package com.example.customerconnect.mapper;

import com.example.customerconnect.dto.CustomerDto;
import com.example.customerconnect.entity.CustomerEntity;

public interface CustomerMapper {

    CustomerEntity dtoToEntity (CustomerDto dto);
    CustomerDto entityToDto (CustomerEntity entity);


}
