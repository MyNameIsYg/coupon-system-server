package com.example.couponhub.dto;

import lombok.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class CouponHubDto {
    private UUID uuid;
    private String category;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private int amount;
    private String details;
    private double price;
    private URL image;
}
