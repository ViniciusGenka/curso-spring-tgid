package com.genka.dtos;

import com.genka.domain.enums.CustomerType;
import com.genka.services.validations.CustomerInsert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@CustomerInsert
public class CustomerNewDTO implements Serializable {
    @NotBlank(message = "email is required")
    @Email(message="invalid email")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "identification is required")
    private String identification;
    @NotNull(message = "type is required")
    private CustomerType type;
    @NotNull(message = "phones is required")
    private Set<String> phones;

    public CustomerNewDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }
}
