package com.genka.domain;

import com.genka.domain.enums.PaymentStatus;

import javax.persistence.Entity;

@Entity
public class CreditCardPayment extends Payment {
    private static final long serialVersionUID = 1L;
    private Integer installmentsCount;

    public CreditCardPayment() {

    }

    public CreditCardPayment(Integer id, PaymentStatus status, Order order, Integer installmentsCount) {
        super(id, status, order);
        this.installmentsCount = installmentsCount;
    }

    public Integer getInstallmentsCount() {
        return installmentsCount;
    }

    public void setInstallmentsCount(Integer installmentsCount) {
        this.installmentsCount = installmentsCount;
    }
}
