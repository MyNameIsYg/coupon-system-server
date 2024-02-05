package com.example.customerconnect.controller;

import com.example.customerconnect.controller.ex.UserNotFoundException;
import com.example.customerconnect.dto.CustomerDto;
import com.example.customerconnect.dto.UserDTO;
import com.example.customerconnect.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final RestTemplate restTemplate;

    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> addCustomer(@RequestHeader("Authorization") String token,
                                                    @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.addCustomer(customerDto, getUser(token)));
    }

    @GetMapping("/customers/details")
    public ResponseEntity<CustomerDto> getCustomer(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(customerService.getCustomer(getUser(token)));
    }

    @GetMapping("/customers/all")
    public ResponseEntity<Set<CustomerDto>> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomers());

    }

    @PutMapping("/customers/update")
    public ResponseEntity<CustomerDto> customerUpdateDetails(@RequestHeader("Authorization") String token, @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.customerUpdateDetails(getUser(token), customerDto));
    }

    @DeleteMapping("/customers/delete")
    public void deleteCustomer(@RequestHeader("Authorization") String token) {
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
        } else throw new UserNotFoundException("User not found");
    }
}
