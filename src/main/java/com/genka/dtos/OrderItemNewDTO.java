package com.genka.dtos;

import java.io.Serializable;

public class OrderItemNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer productId;
    private Integer quantity;

    public OrderItemNewDTO() {
    }

    public OrderItemNewDTO(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
