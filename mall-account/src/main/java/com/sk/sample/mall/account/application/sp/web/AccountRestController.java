package com.sk.sample.mall.account.application.sp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sk.sample.mall.account.domain.model.Account;
import com.sk.sample.mall.account.domain.service.AccountService;

@RestController
@RequestMapping("/v1/accounts")
public class AccountRestController implements AccountService {
	@Autowired
	private AccountService accountService;

	@Override
	@GetMapping
	public List<Account> findAll() {
		return accountService.findAll(); 
	}
	
	@Override
	@GetMapping("/{id}")
	public Account findById(@PathVariable("id") Long id) {
		return accountService.findById(id);
	}

	@Override
	@PostMapping
	public Account register(@RequestBody Account account) {
		return accountService.register(account);
	}

	@Override
	@PutMapping("/{id}")
	public Account update(@PathVariable("id") Long id, @RequestBody Account account) {
		return accountService.update(id, account);
	}

	@Override
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		accountService.delete(id);
	}
	
	@Override
	@GetMapping("/search/name")
	public List<Account> findByNameLike(@RequestParam("name") String name) {
		return accountService.findByNameLike(name);
	}

	@Override
	@GetMapping("/search/email")
	public Account findByEmail(@RequestParam("email") String email) {
		return accountService.findByEmail(email);
	}

	@Override
	//@GetMapping
	public Page<Account> findAll(Pageable pageable) {
		return accountService.findAll(pageable); 
	}


}
