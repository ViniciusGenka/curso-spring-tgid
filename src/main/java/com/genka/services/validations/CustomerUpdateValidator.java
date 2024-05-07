package com.genka.services.validations;

import com.genka.domain.customer.Customer;
import com.genka.dtos.CustomerUpdateDTO;
import com.genka.repositories.CustomerRepository;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
import java.util.Optional;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerUpdateDTO> {

    private final HttpServletRequest request;
    private final CustomerRepository customerRepository;

    public CustomerUpdateValidator(HttpServletRequest request, CustomerRepository customerRepository) {
        this.request = request;
        this.customerRepository = customerRepository;
    }

    @Override
    public void initialize(CustomerUpdate ann) {
    }

    @Override
    public boolean isValid(CustomerUpdateDTO customerUpdateDTO, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        Optional<Customer> customer = customerRepository.findCustomerByEmail(customerUpdateDTO.getEmail());
        return customer.isEmpty() || customer.get().getId().equals(uriId);
    }
}