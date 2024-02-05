package com.example.customerconnect.mapper;

import com.example.customerconnect.dto.CustomerDto;
import com.example.customerconnect.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public CustomerEntity dtoToEntity(CustomerDto dto) {
        return CustomerEntity.builder()
                .uuid(dto.getUuid())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
    }

    @Override
    public CustomerDto entityToDto(CustomerEntity entity) {
        return CustomerDto.builder()
                .uuid(entity.getUuid())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .build();
    }
}
