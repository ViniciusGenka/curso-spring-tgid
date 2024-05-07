package com.genka.resources;

import com.genka.domain.customer.Customer;
import com.genka.dtos.CustomerNewDTO;
import com.genka.dtos.CustomerUpdateDTO;
import com.genka.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody CustomerUpdateDTO customerUpdate, @PathVariable Integer id) {
        Customer updatedCustomer = customerService.updateCustomer(id, customerUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
