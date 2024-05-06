package com.genka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@Profile("dev")
public class SmtpEmailService extends AbstractEmailService {
    @Autowired
    private MailSender mailSender;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage message) {
        mailSender.send(message);
    }

    @Override
    public void sendHtmlEmail(MimeMessage message) {
        javaMailSender.send(message);
    }
}
