package com.sk.sample.mall.order.application.proxy.feign;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientProperties.FeignClientConfiguration;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.sk.sample.mall.order.application.proxy.feign.dto.product.Product;

@Service
public class ProductProxy {
 
	@Autowired
	private ProductClient productClient;
	
	public Product findProduct(Long id) {
		return productClient.findProduct(id).getContent();
	}

	public Collection<Product> findAllProducts() {
		return productClient.findAllProducts().getContent();
	}
	
	public Collection<Product> findAllProducts(int size) {
		return productClient.findAllProducts(size).getContent();
	}
	
	public Product findProductByName(String name) {
		return productClient.findProduct(name);
	}

	@FeignClient(name="products", url="http://localhost:11002", configuration=FeignClientConfiguration.class)
	interface ProductClient {
		@GetMapping("products/{id}")
		Resource<Product> findProduct(@PathVariable("id") Long id);
		
		@GetMapping("products")
		Resources<Product> findAllProducts();
		
		@GetMapping("products")
		Resources<Product> findAllProducts(@RequestParam("size") int size);
		
		@GetMapping("products/search/findByName")
		Product findProduct(@RequestParam(value="name", required=true) String name);
	}
}

