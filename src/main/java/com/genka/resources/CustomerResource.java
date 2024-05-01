package com.genka.resources;

import com.genka.domain.Customer;
import com.genka.dtos.CustomerNewDTO;
import com.genka.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerResource {
    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PostMapping()
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody CustomerNewDTO customerNewDTO) {
        Customer savedCustomer = customerService.saveCustomer(customerService.mapFromDTO(customerNewDTO));
        return ResponseEntity.status(HttpStatus.OK).body(savedCustomer);
    }
}
