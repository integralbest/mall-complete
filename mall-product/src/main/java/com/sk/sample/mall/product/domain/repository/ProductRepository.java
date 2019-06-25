package com.sk.sample.mall.product.domain.repository;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.querydsl.core.types.Predicate;
import com.sk.sample.mall.product.domain.model.ColorType;
import com.sk.sample.mall.product.domain.model.Product;
import com.sk.sample.mall.product.domain.model.SizeType;

@RepositoryRestResource
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>,
                                           QueryDslPredicateExecutor<Product> {
	List<Product> findAll(Predicate predicate); 
	
	Product findByName(@Param("name") String name);
	List<Product> findByProductDescriptionSizeType(@Param("sizeType") SizeType sizeType);
	List<Product> findByProductDescriptionColorType(@Param("colorType") ColorType colorType);
	List<Product> findByPriceValueGreaterThanEqual(@Param("value") Integer value);
	List<Product> findByPriceValueLessThanEqual(@Param("value") Integer value);
	List<Product> findByPriceValueGreaterThanAndPriceValueLessThan(@Param("value1") Integer value1, @Param("value2") Integer value2);
}
