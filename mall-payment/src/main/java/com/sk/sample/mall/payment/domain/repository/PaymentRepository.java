package com.sk.sample.mall.payment.domain.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.sk.sample.mall.payment.domain.model.Payment;

@RepositoryRestResource
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long>, 
	                                       QueryDslPredicateExecutor<Payment> {
	@Override
	@RestResource(exported=false)
	<S extends Payment> S save(S payment);
	
	@Override
	@RestResource(exported=false)
	void delete(Payment payment);
}
