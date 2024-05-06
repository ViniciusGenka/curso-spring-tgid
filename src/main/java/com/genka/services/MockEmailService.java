package com.genka.services;

import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@Profile("test")
public class MockEmailService extends AbstractEmailService{
    private static final Logger LOG = Logger.getLogger(MockEmailService.class.getName());
    @Override
    public void sendEmail(SimpleMailMessage message) {
        LOG.info("Simulando envio de email...");
        LOG.info(message.toString());
    }
}
