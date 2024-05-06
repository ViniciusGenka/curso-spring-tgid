package com.genka.services;

import com.genka.domain.order.Order;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Order order);

    void sendEmail(SimpleMailMessage message);
}