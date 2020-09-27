package com.microservice.Paymentms.controller;

import com.microservice.Paymentms.Service.PaymentService;
import com.microservice.Paymentms.model.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class PaymentController {
    private final PaymentService paymentService;
    private final RestTemplate restTemplate;

    public PaymentController(PaymentService paymentService, RestTemplate restTemplate) {
        this.paymentService = paymentService;
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/success", method = RequestMethod.POST)
    public void addPayment(@RequestBody List<Integer> list){
        int total = 0;
        for(int i = 0; i<list.size(); i++){
            total += list.get(i);
        }
        Payment payment = new Payment();
        payment.setTotal(total);
        paymentService.createPayment(payment);
    }
}
