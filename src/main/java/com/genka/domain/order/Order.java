package com.genka.domain.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.genka.domain.customer.Customer;
import com.genka.domain.address.Address;
import com.genka.domain.payments.Payment;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date timestamp = new Date();
    @OneToOne(cascade = CascadeType.ALL, mappedBy="order")
    private Payment payment;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "destination_address_id")
    private Address destinationAddress;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItem> items = new HashSet<>();
    private Double totalPrice;

    public Order() {
    }

    public Order(Integer id, Customer customer, Address destinationAddress, Set<OrderItem> items) {
        this.id = id;
        this.customer = customer;
        this.destinationAddress = destinationAddress;
        this.items = items;
        this.totalPrice = items.stream().map(item -> getTotalPrice()).reduce(0.0, Double::sum);
    }

    public Order(Customer customer, Address destinationAddress) {
        this.customer = customer;
        this.destinationAddress = destinationAddress;
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
        this.totalPrice = items.stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("Número do Pedido: ");
        builder.append(getId());
        builder.append(", Timestamp: ");
        builder.append(sdf.format(getTimestamp()));
        builder.append(", Cliente: ");
        builder.append(getCustomer().getName());
        builder.append(", Situação do Pagamento: ");
        builder.append(getPayment().getStatus().getDescription());
        builder.append("\nDetalhes:\n");
        for (OrderItem item : getItems()) {
            builder.append(item.toString());
        }
        builder.append("Valor total: ");
        builder.append(nf.format(getTotalPrice()));
        return builder.toString();
    }
}
