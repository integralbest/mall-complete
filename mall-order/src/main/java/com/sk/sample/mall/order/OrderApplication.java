package com.sk.sample.mall.order;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.sk.sample.mall.order.domain.model.CreditCard;
import com.sk.sample.mall.order.domain.model.Order;
import com.sk.sample.mall.order.domain.repository.OrderRepository;
import com.sk.sample.mall.order.domain.service.OrderService;
import com.sk.sample.mall.shared.domain.Address;

@SpringBootApplication
@EnableFeignClients
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner createSampleData(OrderRepository orderRepository, @Qualifier("orderLogic") OrderService orderService) {	
		return (args) -> {
			Order order = new Order(1L, 1L, 3);
			orderRepository.save(order);
			orderService.purchase(order.getId());
		
			order.setCreditCard(new CreditCard("12341234", "0921"));
			orderRepository.save(order);
			orderService.purchase(order.getId());
			
			order.setShippingAddress(new Address(12345, "부산"));
			orderRepository.save(order);
			orderService.purchase(order.getId());
		};
	}

}
