package com.sk.sample.mall.order.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.sample.mall.order.application.proxy.feign.AccountProxy;
import com.sk.sample.mall.order.application.proxy.feign.ProductProxy;
import com.sk.sample.mall.order.application.proxy.feign.dto.account.Account;
import com.sk.sample.mall.order.application.proxy.feign.dto.product.Product;
import com.sk.sample.mall.order.domain.model.Order;
import com.sk.sample.mall.order.domain.repository.OrderRepository;

@Service("orderLogic")
public class OrderLogic implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AccountProxy accountProxy;
	
	@Autowired
	private ProductProxy productProxy;
	
	public void purchase(Long orderId) {
		Order order = orderRepository.findOne(orderId);
		
		if(order == null) {
			System.err.println("no purchase");
			return;
		}
		
		System.out.println("Purchase: " + order.toString());
		
		if(order.getPurchased() == true) {
			System.err.println("already purchased");
			return;
		}
		if(order.getCreditCard() == null) {
			System.err.println("no credit card");
			return;
		}
		
		if(order.getShippingAddress() == null) {
			System.err.println("no shippig address");
			return;
		}
		
		Account account = accountProxy.findAccount(order.getBuyerAccountId());
		System.out.println("Buyer: " + account.toString());
			
		Product product = productProxy.findProduct(order.getProductId());
		System.out.println("Product: " + product.toString());
			
		order.setTotalPrice(order.getProductCount() * product.getPrice().getValue());
		order.setPurchased(true);
		System.out.println("Order: " + order.toString());
			
		orderRepository.save(order);
	}
}
