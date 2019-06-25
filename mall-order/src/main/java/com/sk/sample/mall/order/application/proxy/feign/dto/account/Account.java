package com.sk.sample.mall.order.application.proxy.feign.dto.account;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.sk.sample.mall.shared.domain.Address;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Account {
	private String email;
	private String name;
	
	@Enumerated(EnumType.STRING)
	private MemberType memberType;
	
	@Enumerated(EnumType.STRING)
	private MembershipLevelType membershipLevelType;
	
	private Address address;
	
	public Account(String email, String name, MemberType memberType) {
		this(email, name, memberType, MembershipLevelType.SILVER);
	}
	
	public Account(String email, String name, MemberType memberType, MembershipLevelType membershipLevelType) {
		this.email = email;
		this.name = name;
		this.memberType = memberType;
		this.membershipLevelType = membershipLevelType;
	}
}

