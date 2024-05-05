package com.genka.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genka.domain.payments.BankSlipPayment;
import com.genka.domain.payments.CreditCardPayment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(CreditCardPayment.class);
                objectMapper.registerSubtypes(BankSlipPayment.class);
                super.configure(objectMapper);
            }
        };
    }
}