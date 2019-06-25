package com.sk.sample.mall.order.application.proxy.feign.dto.product;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

@Data
public class ProductDescription {
	@Enumerated(EnumType.STRING)
	private ColorType colorType;
	
	@Enumerated(EnumType.STRING)
	private SizeType sizeType;

	public ProductDescription(ColorType colorType, SizeType sizeType) {
		this.colorType = colorType;
		this.sizeType = sizeType;
	}
}

