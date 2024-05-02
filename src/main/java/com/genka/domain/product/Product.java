package com.genka.domain.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.genka.domain.order.Order;
import com.genka.domain.order.OrderItem;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;
    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name="PRODUCT_CATEGORY",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();
    @JsonBackReference
    @OneToMany(mappedBy = "product")
    private Set<OrderItem> items = new HashSet<>();

    public Product() {
    }

    public Product(String name, Double price, List<Category> categories) {
        this.name = name;
        this.price = price;
        this.categories = categories;
    }

    public Product(Integer id, String name, Double price, List<Category> categories, Set<OrderItem> items) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categories = categories;
        this.items = items;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    @JsonIgnore
    public List<Order> getOrders() {
        return items.stream()
                .map(OrderItem::getOrder)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(categories, product.categories) && Objects.equals(items, product.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, categories, items);
    }
}
