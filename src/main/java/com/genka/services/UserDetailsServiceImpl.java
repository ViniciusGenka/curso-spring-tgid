package com.genka.services;

import com.genka.domain.customer.Customer;
import com.genka.repositories.CustomerRepository;
import com.genka.resources.exceptions.EntityNotFoundException;
import com.genka.security.UserAuthDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final CustomerRepository customerRepository;

    public UserDetailsServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByEmail(email).orElseThrow(() -> new EntityNotFoundException("Customer with email " + email + " not found"));
        return new UserAuthDetails(
                customer.getId(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getRoles()
        );
    }
}
