package com.genka.domain.enums;

public enum PaymentStatus {
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private int value;
    private String description;

    PaymentStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentStatus toEnum(Integer value) {
        if (value == null) {
            return null;
        }

        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (value.equals(paymentStatus.getValue())) {
                return paymentStatus;
            }
        }

        throw new IllegalArgumentException("Invalid payment status: " + value);
    }
}
