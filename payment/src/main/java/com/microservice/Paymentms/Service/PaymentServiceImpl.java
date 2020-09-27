package com.microservice.Paymentms.Service;

import com.microservice.Paymentms.model.Payment;
import com.microservice.Paymentms.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAllPayment() {
        return null;
    }

    @Override
    public Optional<Payment> getSinglePayment(long paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public void createPayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public Object updatePayment() {
        return null;
    }
}
