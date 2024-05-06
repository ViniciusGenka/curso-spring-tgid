package com.genka.services;

import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class SmtpEmailService extends AbstractEmailService {
    private final MailSender mailSender;

    public SmtpEmailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(SimpleMailMessage message) {
        mailSender.send(message);
    }
}
