package com.example.couponhub.service.ex;

public class NoSuchCouponException extends RuntimeException {
    public NoSuchCouponException(String message) {
        super(message);
    }
}
