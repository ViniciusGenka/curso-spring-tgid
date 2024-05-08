package com.genka.services;

import com.genka.domain.customer.Customer;
import com.genka.domain.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {
    @Value("${default.sender}")
    private String sender;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Order order) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(order);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order order) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(order.getCustomer().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado! Código: " + order.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(order.toString());
        return sm;
    }

    protected MimeMessage prepareMimeMessageFromOrder(Order order) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setTo(order.getCustomer().getEmail());
        messageHelper.setFrom(sender);
        messageHelper.setSubject("Pedido confirmado! Código: " + order.getId());
        messageHelper.setSentDate(new Date(System.currentTimeMillis()));
        messageHelper.setText(prepareHtmlMessageFromOrder(order), true);
        return message;
    }

    protected String prepareHtmlMessageFromOrder(Order order) {
        Context context = new Context();
        context.setVariable("order", order);
        return templateEngine.process("email/orderConfirmation", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Order order) {
        try {
            MimeMessage message = prepareMimeMessageFromOrder(order);
            sendHtmlEmail(message);
        } catch (MessagingException ex) {
            sendOrderConfirmationEmail(order);
        }
    }

    @Override
    public void sendNewPasswordEmail(Customer customer, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(customer, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Customer customer, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(customer.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);
        return sm;
    }
}
