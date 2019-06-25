package com.sk.sample.mall.product;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import com.querydsl.core.types.Predicate;
import com.sk.sample.mall.product.domain.model.ColorType;
import com.sk.sample.mall.product.domain.model.Money;
import com.sk.sample.mall.product.domain.model.Product;
import com.sk.sample.mall.product.domain.model.ProductDescription;
import com.sk.sample.mall.product.domain.model.QProduct;
import com.sk.sample.mall.product.domain.model.SizeType;
import com.sk.sample.mall.product.domain.repository.ProductRepository;

@SpringBootApplication
@EnableHypermediaSupport(type=EnableHypermediaSupport.HypermediaType.HAL)
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner execSampleCode(ProductRepository accountRepository) {	
		return (args) -> {
			insertProducts(accountRepository);
			displayProducts(accountRepository);
			
			executeExample2(accountRepository);
			executeExample3(accountRepository);
			executeExample4(accountRepository);
			executeExample5(accountRepository);
			executeExample6(accountRepository);
			executeExample7(accountRepository);
		};
	}
	
	public void insertProducts(ProductRepository productRepository) {
		Product product1 = new Product("Iron Man", new Money(30000), new ProductDescription(ColorType.RED, SizeType.L));
		productRepository.save(product1);
		
		Product product2 = new Product("Captain America", new Money(20000), new ProductDescription(ColorType.BLUE, SizeType.M));
		productRepository.save(product2);
		
		Product product3 = new Product("Winter Soldier", new Money(15000), new ProductDescription(ColorType.BLUE, SizeType.M));
		productRepository.save(product3);
	}
	
	public void displayProducts(ProductRepository productRepository) {
		System.out.println("***************************************************************");
		
		Iterable<Product> productList = productRepository.findAll();
		for(Product product : productList) {
			System.out.println(product.toString());	
		}
		
		System.out.println("***************************************************************");
	}
	
	public void executeExample2(ProductRepository productRepository) {
		Product product = productRepository.findByName("Iron Man");
		
		product.setPrice(new Money(25000));
		productRepository.save(product);
		
		displayProducts(productRepository);
	}
	
	public void executeExample3(ProductRepository productRepository) {
		List<Product> product = productRepository.findByProductDescriptionSizeType(SizeType.M);
		System.out.println("Result: " + product.toString());
	}
	
	public void executeExample4(ProductRepository productRepository) {
		List<Product> product = productRepository.findByProductDescriptionColorType(ColorType.BLUE);
		System.out.println("Result: " + product.toString());
	}
	
	public void executeExample5(ProductRepository productRepository) {
		List<Product> product = productRepository.findByPriceValueGreaterThanEqual(17000);
		System.out.println("Result: " + product.toString());
		
		List<Product> product2 = productRepository.findAll(QProduct.product.price.value.goe(17000));
		System.out.println("Result2: " + product2.toString());
	}
	
	public void executeExample6(ProductRepository productRepository) {
		List<Product> product = productRepository.findByPriceValueLessThanEqual(21000);
		System.out.println("Result: " + product.toString());
		
		List<Product> product2 = productRepository.findAll(QProduct.product.price.value.loe(17000));
		System.out.println("Result2: " + product2.toString());
	}

	public void executeExample7(ProductRepository productRepository) {
		List<Product> product = productRepository.findByPriceValueGreaterThanAndPriceValueLessThan(10000, 20000);
		System.out.println("Result: " + product.toString());
		
		Predicate predicate = QProduct.product.price.value.gt(10000).and(
				              QProduct.product.price.value.lt(20000));
		List<Product> product2 = productRepository.findAll(predicate);
		System.out.println("Result2: " + product2.toString());
	}
}
