package com.genka.repositories;

import com.genka.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Transactional(readOnly=true)
    Optional<Customer> findCustomerByEmail(String email);
}
