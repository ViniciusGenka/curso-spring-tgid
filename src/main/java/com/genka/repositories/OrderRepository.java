package com.genka.repositories;

import com.genka.domain.customer.Customer;
import com.genka.domain.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Transactional(readOnly = true)
    Page<Order> findByCustomer(Customer customer, Pageable pageable);
}
