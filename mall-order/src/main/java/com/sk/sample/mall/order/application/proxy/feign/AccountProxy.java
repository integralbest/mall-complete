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

import com.sk.sample.mall.order.application.proxy.feign.dto.account.Account;

@Service
public class AccountProxy {
	@Autowired
	private AccountClient accountClient;
	
	public Account findAccount(Long id) {
		return accountClient.findAccount(id).getContent();
	}

	public Collection<Account> findAllAccounts() {
		return accountClient.findAllAccounts().getContent();
	}
	
	public Collection<Account> findAllAccounts(int size) {
		return accountClient.findAllAccounts(size).getContent();
	}
	
	public Account findAccounByName(String name) {
		return accountClient.findAccount(name);
	}

	@FeignClient(name="accounts", url="http://localhost:11001", configuration=FeignClientConfiguration.class)
	interface AccountClient {
		@GetMapping("accounts/{id}")
		Resource<Account> findAccount(@PathVariable("id") Long id);
		
		@GetMapping("accounts")
		Resources<Account> findAllAccounts();
		
		@GetMapping("accounts")
		Resources<Account> findAllAccounts(@RequestParam("size") int size);
		
		@GetMapping("accounts/search/findByName")
		Account findAccount(@RequestParam(value="name", required=true) String name);
	}
}

