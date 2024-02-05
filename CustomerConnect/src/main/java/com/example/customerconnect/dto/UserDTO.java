package com.example.customerconnect.dto;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userName;
    private UUID uuid;
}
