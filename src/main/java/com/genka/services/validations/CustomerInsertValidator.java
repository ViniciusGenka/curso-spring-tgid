package com.genka.services.validations;

import com.genka.domain.customer.Customer;
import com.genka.domain.enums.CustomerType;
import com.genka.dtos.CustomerNewDTO;
import com.genka.repositories.CustomerRepository;
import com.genka.resources.exceptions.ValidationErrorDetails;
import com.genka.services.validations.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerNewDTO> {
    private final CustomerRepository customerRepository;

    public CustomerInsertValidator(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void initialize(CustomerInsert ann) {
    }

    @Override
    public boolean isValid(CustomerNewDTO customerNewDTO, ConstraintValidatorContext context) {

        List<ValidationErrorDetails> list = new ArrayList<>();

        if (customerNewDTO.getType().equals(CustomerType.PESSOA_FISICA) && !BR.isValidCPF(customerNewDTO.getIdentification())) {
            list.add(new ValidationErrorDetails("identification", "CPF inválido"));
        }

        if (customerNewDTO.getType().equals(CustomerType.PESSOA_JURIDICA) && !BR.isValidCNPJ(customerNewDTO.getIdentification())) {
            list.add(new ValidationErrorDetails("identification", "CNPJ inválido"));
        }

        Optional<Customer> customer = customerRepository.findCustomerByEmail(customerNewDTO.getEmail());
        if (customer.isPresent()) {
            list.add(new ValidationErrorDetails("email", "email already exists"));
        }

        for (ValidationErrorDetails e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
