package com.genka.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class CustomerUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotBlank(message = "email is required")
    @Email(message="invalid email")
    private String email;
    @NotBlank(message = "name is required")
    private String name;

    public CustomerUpdateDTO() {
    }

    public CustomerUpdateDTO(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
