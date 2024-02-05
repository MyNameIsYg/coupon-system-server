package com.example.couponhub.service;

import com.example.couponhub.dto.CouponHubDto;
import com.example.couponhub.dto.UserDTO;
import com.example.couponhub.entity.CouponHubEntity;
import com.example.couponhub.mapper.CouponHubMapper;
import com.example.couponhub.repository.CouponHubRepository;
import com.example.couponhub.service.ex.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponHubServiceImpl implements CouponHubService {

    private final CouponHubRepository couponHubRepository;
    private final CouponHubMapper couponHubMapper;

    @Override
    public CouponHubDto getCoupon(UUID couponUuid) {
        Optional<CouponHubEntity> optCoupon = couponHubRepository.findByUuid(couponUuid);

        if (optCoupon.isEmpty()) {
            throw new NoSuchCouponException("Coupon not found");
        }
        return couponHubMapper.entityToDto(optCoupon.get());
    }

    @Override
    public Set<CouponHubDto> getAllCouponOfCustomer(UserDTO userDTO) {

        Set<CouponHubEntity> purchasedCouponByUuid = couponHubRepository.findAllByCustomersContains(userDTO.getUuid());

        return purchasedCouponByUuid.stream()
                .map(couponHubMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<CouponHubDto> getAllCouponsOfCompany(UserDTO userDTO) {

        Set<CouponHubEntity> purchasedCouponByUuid = couponHubRepository.findByCompanyUuid(userDTO.getUuid());

        return purchasedCouponByUuid.stream()
                .map(couponHubMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public CouponHubDto purchase(UserDTO userDTO, UUID couponUuid) {

        Optional<CouponHubEntity> optCoupon = couponHubRepository.findByUuid(couponUuid);

        if (optCoupon.isEmpty()) {
            throw new NoSuchCouponException("Coupon not found");
        }

        CouponHubEntity couponHubEntity = optCoupon.get();

        Set<UUID> customers = couponHubEntity.getCustomers();

        if (customers.contains(userDTO.getUuid())) {
            throw new CouponAlreadyPurchasedException("Coupon already purchased");
        }
        if (couponHubEntity.getAmount() <= 0) {
            throw new OOSException("Coupon OOS");
        }
        if (couponHubEntity.getStartDate().isAfter(LocalDate.now())) {
            throw new CouponNotAvailableException("Coupon not available");
        }
        if (couponHubEntity.getEndDate().isBefore(LocalDate.now())) {
            throw new CouponExpiredException("Coupon is expired");
        }

        couponHubEntity.setAmount(couponHubEntity.getAmount() - 1);

        customers.add(userDTO.getUuid());
        couponHubEntity.setCustomers(customers);
        couponHubRepository.save(couponHubEntity);
        return couponHubMapper.entityToDto(couponHubEntity);

    }

    @Override
    public void deleteCustomerCoupon(UUID customerUuid) {
        Set<CouponHubEntity> coupons = couponHubRepository.findAllByCustomersContains(customerUuid);

        for (CouponHubEntity coupon : coupons) {
            coupon.getCustomers().remove(customerUuid);
            couponHubRepository.save(coupon);
        }
    }
}
