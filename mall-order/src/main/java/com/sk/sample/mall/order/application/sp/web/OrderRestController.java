package com.sk.sample.mall.order.application.sp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.sample.mall.order.domain.service.OrderService;

@RestController
@RequestMapping("/v1/orders")
public class OrderRestController implements OrderService {
	@Autowired
	private OrderService orderService;

	@Override
	@PutMapping("/{id}/purchased")
	public void purchase(@PathVariable("id") Long id) {
		orderService.purchase(id);
	}
}
