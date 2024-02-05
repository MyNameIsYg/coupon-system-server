package com.example.couponhub.service.ex;

public class CouponNotAvailableException extends RuntimeException {
    public CouponNotAvailableException(String message) {
        super(message);
    }
}
