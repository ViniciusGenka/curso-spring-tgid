package com.genka.services;

import com.genka.domain.customer.Customer;
import com.genka.domain.order.Order;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Order order);

    void sendEmail(SimpleMailMessage message);

    void sendOrderConfirmationHtmlEmail(Order order);

    void sendHtmlEmail(MimeMessage message);

    void sendNewPasswordEmail(Customer customer, String newPassword);
}