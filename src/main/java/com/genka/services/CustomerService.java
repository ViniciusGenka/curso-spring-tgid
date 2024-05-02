package com.genka.services;

import com.genka.domain.customer.Customer;
import com.genka.dtos.CustomerNewDTO;
import com.genka.repositories.CustomerRepository;
import com.genka.resources.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> findCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    public Customer getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer with id " + customerId + " not found"));
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer mapFromDTO(CustomerNewDTO customerNewDTO) {
        return new Customer(
                null,
                customerNewDTO.getEmail(),
                customerNewDTO.getName(),
                customerNewDTO.getIdentification(),
                customerNewDTO.getType(),
                customerNewDTO.getPassword(),
                customerNewDTO.getPhones()
        );
    }
}