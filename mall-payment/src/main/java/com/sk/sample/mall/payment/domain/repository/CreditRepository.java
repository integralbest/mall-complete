package com.sk.sample.mall.payment.domain.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sk.sample.mall.payment.domain.model.Credit;

@RepositoryRestResource
public interface CreditRepository extends PagingAndSortingRepository<Credit, Long>, 
	                                      QueryDslPredicateExecutor<Credit> {
	Credit findByCreditCardCardNumber(@Param("cardNumber") String cardNumber);
}
