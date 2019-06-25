package com.sk.sample.mall.product.domain.model;

import javax.persistence.Embeddable;

import com.sk.sample.mall.shared.base.ValueObject;

import lombok.Data;


@Data
@Embeddable
public class Money implements ValueObject {
	private Integer value;
	
	public Money(Integer value) {
		this.value = value;
	}
}

