package com.genka.services;

import com.genka.domain.address.Address;
import com.genka.domain.customer.Customer;
import com.genka.domain.enums.PaymentStatus;
import com.genka.domain.order.Order;
import com.genka.domain.order.OrderItem;
import com.genka.domain.payments.BankSlipPayment;
import com.genka.domain.product.Product;
import com.genka.dtos.OrderItemNewDTO;
import com.genka.dtos.OrderNewDTO;
import com.genka.repositories.OrderRepository;
import com.genka.resources.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CustomerService customerService;
    private final AddressService addressService;
    private final PaymentService paymentService;
    private final EmailService emailService;

    public OrderService(OrderRepository orderRepository, ProductService productService, CustomerService customerService, AddressService addressService, PaymentService paymentService, EmailService emailService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.customerService = customerService;
        this.addressService = addressService;
        this.paymentService = paymentService;
        this.emailService = emailService;
    }

    public Optional<Order> findOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order with id " + orderId + " not found"));
    }

    public Order saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        emailService.sendOrderConfirmationHtmlEmail(order);
        return savedOrder;
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
        if(orderNewDTO.getPayment() instanceof BankSlipPayment) {
            paymentService.setBankSlipPaymentExpirationDate((BankSlipPayment) orderNewDTO.getPayment(), new Date());
        }
        orderNewDTO.getPayment().setStatus(PaymentStatus.PENDENTE);
        orderNewDTO.getPayment().setOrder(order);
        order.setPayment(orderNewDTO.getPayment());
        order.setItems(items);
        return order;
    }
}
