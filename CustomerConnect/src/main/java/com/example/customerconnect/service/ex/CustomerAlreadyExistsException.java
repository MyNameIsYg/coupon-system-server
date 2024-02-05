package com.example.customerconnect.service.ex;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String message) {
    super(message);
    }

}
