package com.sk.sample.mall.payment.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.sample.mall.payment.domain.model.Credit;
import com.sk.sample.mall.payment.domain.model.CreditCard;
import com.sk.sample.mall.payment.domain.model.Payment;
import com.sk.sample.mall.payment.domain.repository.CreditRepository;
import com.sk.sample.mall.payment.domain.repository.PaymentRepository;

@Service
public class PaymentLogic implements PaymentService {
	@Autowired
	private CreditRepository creditRepository;
	
	@Autowired
	private PaymentRepository purchaseRepository;
	
	@Override
	public Payment pay(Payment payment) {
		payment.setSuccessed(false);
		payment.setPurchasedDate(new Date());
		
		CreditCard requestedCreditCard = payment.getCreditCard();
		Integer amount = payment.getPurchasedAmount();
		
		Credit credit = creditRepository.findByCreditCardCardNumber(requestedCreditCard.getCardNumber());
		
		if(credit == null) {
			System.err.println("no credit");
			return payment;
		}
		
		if(!credit.getCreditCard().getValidThru().equals(requestedCreditCard.getValidThru())) {
			System.err.println("not matched validThru");
			return payment;
		}
		
		/* Valid Thru 유효성 체크 */
		
		if(credit.getUsedAmount() + amount > credit.getLimitAmount()) {
			System.err.println("한도 초과");
			return payment;
		}
		
		credit.setUsedAmount(credit.getUsedAmount() + amount);
		creditRepository.save(credit);
		
		payment.setSuccessed(true);
		purchaseRepository.save(payment);
		
		return payment;
	}
}
