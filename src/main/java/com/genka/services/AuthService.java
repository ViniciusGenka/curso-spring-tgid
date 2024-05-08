package com.genka.services;

import com.genka.domain.customer.Customer;
import com.genka.repositories.CustomerRepository;
import com.genka.resources.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public void sendNewPassword(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email).orElseThrow(() -> new EntityNotFoundException("Customer with email " + email + " not found"));
        String newPassword = newPassword();
        customer.setPassword(bcrypt.encode(newPassword));
        customerRepository.save(customer);
        emailService.sendNewPasswordEmail(customer, newPassword);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if (opt == 0) { // gera um digito
            return (char) (random.nextInt(10) + 48);
        } else if (opt == 1) { // gera letra maiuscula
            return (char) (random.nextInt(26) + 65);
        } else { // gera letra minuscula
            return (char) (random.nextInt(26) + 97);
        }
    }
}