package com.example.couponhub.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

import java.net.URL;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponHubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JdbcType(CharJdbcType.class)
    private UUID uuid;
    private UUID companyUuid;
    private String category;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private int amount;
    private String details;
    private double price;
    private URL image;
    @ElementCollection
    @CollectionTable
    @JdbcType(CharJdbcType.class)
    private Set<UUID> customers;


}
