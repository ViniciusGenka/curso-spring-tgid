package com.genka.services;

import com.genka.domain.payments.BankSlipPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class PaymentService {

    public void setBankSlipPaymentExpirationDate(BankSlipPayment bankSlipPayment, Date paymentTimestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paymentTimestamp);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        bankSlipPayment.setExpirationDate(calendar.getTime());
    }
}