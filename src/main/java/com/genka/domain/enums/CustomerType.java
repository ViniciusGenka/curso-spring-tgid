package com.genka.domain.enums;

public enum CustomerType {
    PESSOA_FISICA(1, "Pessoa Física"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");

    private int value;
    private String description;

    CustomerType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static CustomerType toEnum(Integer value) {
        if (value == null) {
            return null;
        }

        for (CustomerType customerType : CustomerType.values()) {
            if (value.equals(customerType.getValue())) {
                return customerType;
            }
        }

        throw new IllegalArgumentException("Invalid customer type: " + value);
    }
}
