package com.example.couponhub.service;

import com.example.couponhub.dto.CouponHubDto;
import com.example.couponhub.dto.UserDTO;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CouponHubService {

    CouponHubDto getCoupon(UUID couponUuid);

    Set<CouponHubDto> getAllCouponOfCustomer (UserDTO userDTO);

    Set<CouponHubDto> getAllCouponsOfCompany(UserDTO userDTO);

    CouponHubDto purchase(UserDTO userDTO, UUID couponUuid);

    void deleteCustomerCoupon(UUID customerUuid);
}

