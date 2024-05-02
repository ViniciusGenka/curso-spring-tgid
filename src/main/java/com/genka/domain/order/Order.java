package com.genka.domain.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.genka.domain.customer.Customer;
import com.genka.domain.address.Address;
import com.genka.domain.payments.Payment;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date timestamp;

    @OneToOne(cascade = CascadeType.ALL, mappedBy="order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "destination_address_id")
    private Address destinationAddress;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items = new HashSet<>();

    public Order() {
    }

    public Order(Integer id, Date timestamp, Payment payment, Customer customer, Address destinationAddress, Set<OrderItem> items) {
        this.id = id;
        this.timestamp = timestamp;
        this.payment = payment;
        this.customer = customer;
        this.destinationAddress = destinationAddress;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(Address destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(timestamp, order.timestamp) && Objects.equals(payment, order.payment) && Objects.equals(customer, order.customer) && Objects.equals(destinationAddress, order.destinationAddress) && Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, payment, customer, destinationAddress, items);
    }
}
