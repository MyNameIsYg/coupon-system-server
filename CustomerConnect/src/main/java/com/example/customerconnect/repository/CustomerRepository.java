package com.example.customerconnect.repository;

import com.example.customerconnect.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository <CustomerEntity,Long> {


    Optional<CustomerEntity> findByUuid(UUID uuid);
}
