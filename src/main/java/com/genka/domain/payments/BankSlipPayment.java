package com.genka.domain.payments;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.genka.domain.order.Order;
import com.genka.domain.enums.PaymentStatus;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonTypeName("bankSlipPayment")
public class BankSlipPayment extends Payment{
    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date expirationDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
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
