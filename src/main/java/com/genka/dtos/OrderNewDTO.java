package com.genka.dtos;

import java.io.Serializable;
import java.util.List;

public class OrderNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer customerId;
    private Integer destinationAddressId;
    private List<OrderItemNewDTO> items;

    public OrderNewDTO() {
    }

    public OrderNewDTO(Integer customerId, Integer destinationAddressId, List<OrderItemNewDTO> items) {
        this.customerId = customerId;
        this.destinationAddressId = destinationAddressId;
        this.items = items;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getDestinationAddressId() {
        return destinationAddressId;
    }

    public void setDestinationAddressId(Integer destinationAddressId) {
        this.destinationAddressId = destinationAddressId;
    }

    public List<OrderItemNewDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemNewDTO> items) {
        this.items = items;
    }
}
