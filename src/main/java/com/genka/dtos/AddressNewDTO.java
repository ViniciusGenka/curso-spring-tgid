package com.genka.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddressNewDTO {
    @NotBlank(message = "street is required")
    private String street;
    @NotBlank(message = "number is required")
    private String number;
    @NotBlank(message = "complement is required")
    private String complement;
    @NotBlank(message = "neighborhood is required")
    private String neighborhood;
    @NotBlank(message = "zipCode is required")
    private String zipCode;
    @NotNull(message = "cityId is required")
    private Integer cityId;
    private Integer userId;

    public AddressNewDTO() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
