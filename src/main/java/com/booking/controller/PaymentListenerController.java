package com.booking.controller;

import com.booking.service.PaymentListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentListenerController {

    private PaymentListenerService paymentListenerService;

    @Autowired
    public PaymentListenerController(PaymentListenerService paymentListenerService) {
        this.paymentListenerService = paymentListenerService;
    }

    @PostMapping("/payment/{bookingId}/{status}")
    public void listen(@PathVariable Long bookingId, String status) {
        paymentListenerService.listen(bookingId, status);
    }

}
