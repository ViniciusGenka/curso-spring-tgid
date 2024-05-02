package com.genka.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoryNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotEmpty(message="name is required")
    @Length(min=1, max=80, message = "name length must be between 1 and 80 characters")
    private String name;

    public CategoryNewDTO() {
    }

    public CategoryNewDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
