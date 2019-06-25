package com.sk.sample.mall.order.application.proxy.feign.dto.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Product {
	private String name;
	private Money price;
	
	private ProductDescription productDescription;
	
	public Product(String name, Money price, ProductDescription productDescription) {
		this.name = name;
		this.price = price;
		this.productDescription = productDescription;
	}
}

