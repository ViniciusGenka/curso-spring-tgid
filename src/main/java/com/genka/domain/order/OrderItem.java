package com.genka.domain.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.genka.domain.product.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @EmbeddedId
    private OrderItemId id;
    @JsonBackReference
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;
    @JsonManagedReference
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
    private Double discount;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, Double discount, Integer quantity, Double unitPrice) {
        this.id = new OrderItemId(order.getId(), product.getId());
        this.order = order;
        this.product = product;
        this.discount = discount;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = (unitPrice - discount) * quantity;
    }

    public OrderItemId getId() {
        return id;
    }

    public void setId(OrderItemId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return getProduct().getName() +
                ", Quantidade: " +
                getQuantity() +
                ", Preço unitário: " +
                nf.format(getUnitPrice()) +
                ", Subtotal: " +
                nf.format(getTotalPrice()) +
                "\n";
    }
}
