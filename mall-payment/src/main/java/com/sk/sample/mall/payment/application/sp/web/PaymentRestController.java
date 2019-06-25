package com.sk.sample.mall.payment.application.sp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.sample.mall.payment.domain.model.Payment;
import com.sk.sample.mall.payment.domain.service.PaymentService;

@RestController
@RequestMapping("/v1/payments")
public class PaymentRestController implements PaymentService {
	@Autowired
	private PaymentService paymentService;
	
	@Override
	@PostMapping
	public Payment pay(@RequestBody Payment purchase) {
		return paymentService.pay(purchase);
	}

}
