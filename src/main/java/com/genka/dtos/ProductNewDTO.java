package com.genka.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotEmpty(message="name is required")
    @Length(min=1, max=80, message = "name length must be between 1 and 80 characters")
    private String name;
    @NotNull(message="price is required")
    @PositiveOrZero(message = "price must be greater than or equal to zero")
    private Double price;
    @NotNull
    @Size(min = 1, message = "product must have at leat one category")
    private List<Integer> categoryIds = new ArrayList<>();

    public ProductNewDTO() {
    }

    public ProductNewDTO(Integer id, String name, Double price, List<Integer> categoryIds) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryIds = categoryIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
