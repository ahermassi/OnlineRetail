package com.microservice.Paymentms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.Paymentms.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
}
