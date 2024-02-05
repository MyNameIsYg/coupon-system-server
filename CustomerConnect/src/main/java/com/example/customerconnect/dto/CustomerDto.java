package com.example.customerconnect.dto;

import lombok.Builder;
import lombok.Data;


import java.util.UUID;

@Data
@Builder
public class CustomerDto {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String email;
}
