package com.example.couponhub.controller;

import com.example.couponhub.controller.ex.UserNotFoundException;
import com.example.couponhub.dto.CouponHubDto;
import com.example.couponhub.dto.UserDTO;
import com.example.couponhub.service.CouponHubService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CouponHubController {

    private final CouponHubService couponHubService;
    private final RestTemplate restTemplate;

    @GetMapping("/coupons/{couponUuid}")
    public ResponseEntity <CouponHubDto> getCoupon(@RequestHeader("Authorization") String token,
                                                            @PathVariable UUID couponUuid) {
        getUser(token);
        return ResponseEntity.ok(couponHubService.getCoupon(couponUuid));
    }

    @GetMapping("/coupons/customer")
    public ResponseEntity<Set<CouponHubDto>> getAllCouponsOfCustomer(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(couponHubService.getAllCouponOfCustomer(getUser(token)));
    }

    @GetMapping("/coupons/company")
    public ResponseEntity<Set<CouponHubDto>> getAllCouponsOfCompany(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(couponHubService.getAllCouponsOfCompany(getUser(token)));
    }

    @PostMapping("/coupons/purchase/{couponUuid}")
    public ResponseEntity<CouponHubDto> purchase(@RequestHeader("Authorization") String token,
                                                           @PathVariable UUID couponUuid) {
        return ResponseEntity.ok(couponHubService.purchase(getUser(token), couponUuid));
    }

    @DeleteMapping("/coupons/deleteCustomerCoupon/{customerUuid}")
    public void deleteCustomerCoupon(@PathVariable UUID customerUuid) {
        couponHubService.deleteCustomerCoupon(customerUuid);
    }

    private UserDTO getUser(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<UserDTO> userDtoResponseEntity = restTemplate.exchange(
                "http://localhost:1337/parse-token",
                HttpMethod.GET,

                new HttpEntity<>(httpHeaders),
                UserDTO.class);

        if (userDtoResponseEntity.getStatusCode().is2xxSuccessful()) {
            return userDtoResponseEntity.getBody();
        }
        throw new UserNotFoundException("User not found");
    }
}
