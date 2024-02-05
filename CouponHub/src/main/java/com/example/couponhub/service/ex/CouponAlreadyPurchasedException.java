package com.example.couponhub.service.ex;

public class CouponAlreadyPurchasedException extends RuntimeException {
    public CouponAlreadyPurchasedException(String message) {
        super(message);
    }
}
