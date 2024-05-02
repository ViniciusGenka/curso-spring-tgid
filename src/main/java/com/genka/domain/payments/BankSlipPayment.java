package com.genka.domain.payments;

import com.genka.domain.order.Order;
import com.genka.domain.enums.PaymentStatus;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class BankSlipPayment extends Payment{
    private static final long serialVersionUID = 1L;
    private Date expirationDate;
    private Date settlementDate;

    public BankSlipPayment() {
    }

    public BankSlipPayment(Integer id, PaymentStatus status, Order order, Date expirationDate, Date settlementDate) {
        super(id, status, order);
        this.expirationDate = expirationDate;
        this.settlementDate = settlementDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }
}
