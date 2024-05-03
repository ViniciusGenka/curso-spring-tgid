package com.genka.domain.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.genka.domain.address.Address;
import com.genka.domain.enums.CustomerType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String email;
    private String name;
    private String identification;
    private Integer type;
    @JsonIgnore
    private String password;
    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "PHONES")
    private Set<String> phones = new HashSet<>();

    public Customer() {
    }

    public Customer(String email, String name, String identification, CustomerType type, String password, Set<String> phones) {
        this.email = email;
        this.name = name;
        this.identification = identification;
        this.type = type.getValue();
        this.password = password;
        this.phones = phones;
    }

    public Customer(Integer id, String email, String name, String identification, CustomerType type, String password, List<Address> addresses, Set<String> phones) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.identification = identification;
        this.type = type.getValue();
        this.password = password;
        this.addresses = addresses;
        this.phones = phones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    //---------------------------------------------
    //GET CUSTOMIZADO PARA RETORNAR UM CustomerType
    //---------------------------------------------
    public CustomerType getType() {
        return CustomerType.toEnum(type);
    }

    //-------------------------------------------------------------
    //SET CUSTOMIZADO PARA DEFINIR O VALOR DO CustomerType RECEBIDO
    //-------------------------------------------------------------
    public void setType(CustomerType customerType) {
        this.type = customerType.getValue();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(email, customer.email) && Objects.equals(name, customer.name) && Objects.equals(identification, customer.identification) && Objects.equals(type, customer.type) && Objects.equals(password, customer.password) && Objects.equals(addresses, customer.addresses) && Objects.equals(phones, customer.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, identification, type, password, addresses, phones);
    }
}
