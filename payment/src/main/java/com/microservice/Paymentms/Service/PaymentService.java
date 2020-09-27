package com.microservice.Paymentms.Service;

import com.microservice.Paymentms.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<Payment> getAllPayment();
    Optional<Payment> getSinglePayment(long paymentId);
    void createPayment(Payment payment);
    Object updatePayment();
}
