package com.bootcamp.challenge.spring.services;

import com.bootcamp.challenge.spring.dtos.CustomerDTO;
import com.bootcamp.challenge.spring.entities.Customer;
import com.bootcamp.challenge.spring.repositories.CustomerRepository;
import com.bootcamp.challenge.spring.shared.exceptions.RepositoryException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    ModelMapper modelMapper;

    public CustomerDTO create(CustomerDTO customer) {
        try {
        customerRepository.create(customerToDTO(customer));
        } catch (IOException e) {

        throw new RepositoryException("Fail to create a new Customer \n\n\n\n" + e.getMessage());
        }
        return customer;
    }

    public List<CustomerDTO> list() {
        return this.customerRepository.list().stream().map(this::customerToDTO).collect(Collectors.toList());
    }

    public CustomerDTO customerToDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public Customer customerToDTO(CustomerDTO customer) {
        return modelMapper.map(customer, Customer.class);
    }
}
