package com.bootcamp.challenge.spring.controllers;

import com.bootcamp.challenge.spring.dtos.CustomerDTO;
import com.bootcamp.challenge.spring.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customer));
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerDTO>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.list());
    }
}
