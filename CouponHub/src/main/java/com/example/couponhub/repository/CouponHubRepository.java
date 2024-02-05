package com.example.couponhub.repository;

import com.example.couponhub.entity.CouponHubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CouponHubRepository extends JpaRepository<CouponHubEntity,Long> {

    Optional<CouponHubEntity> findByUuid(UUID couponUuid);

    Set<CouponHubEntity> findByCompanyUuid(UUID uuid);

    Set<CouponHubEntity> findAllByCustomersContains(UUID customerUuid);
}


