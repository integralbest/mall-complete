package com.sk.sample.mall.shared.base;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;

@MappedSuperclass
@EqualsAndHashCode
public abstract class AbstractEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;

	public Long getId() {
		return id;
	}
}
