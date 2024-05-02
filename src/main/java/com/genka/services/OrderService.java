package com.genka.services;

import com.genka.domain.address.Address;
import com.genka.domain.customer.Customer;
import com.genka.domain.enums.PaymentStatus;
import com.genka.domain.order.Order;
import com.genka.domain.order.OrderItem;
import com.genka.domain.payments.Payment;
import com.genka.domain.product.Product;
import com.genka.dtos.OrderItemNewDTO;
import com.genka.dtos.OrderNewDTO;
import com.genka.repositories.OrderItemRepository;
import com.genka.repositories.OrderRepository;
import com.genka.resources.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final CustomerService customerService;
    private final AddressService addressService;


    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductService productService, CustomerService customerService, AddressService addressService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
        this.customerService = customerService;
        this.addressService = addressService;
    }

    public Optional<Order> findOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order with id " + orderId + " not found"));
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order mapFromDTO(OrderNewDTO orderNewDTO) {
        Customer customer = customerService.getCustomerById(orderNewDTO.getCustomerId());
        Address destinationAddress = addressService.getAddressById(orderNewDTO.getDestinationAddressId());
        Order order = new Order(customer, destinationAddress);
        Set<OrderItem> items = orderNewDTO.getItems().stream().map((OrderItemNewDTO itemNewDTO) -> {
            Product product = productService.getProductById(itemNewDTO.getProductId());
            return new OrderItem(
                    order,
                    product,
                    0.0,
                    itemNewDTO.getQuantity(),
                    product.getPrice()
            );
        }).collect(Collectors.toSet());
        Payment payment = new Payment(null, PaymentStatus.PENDENTE, order);
        order.setItems(items);
        order.setPayment(payment);
        return order;
    }
}
