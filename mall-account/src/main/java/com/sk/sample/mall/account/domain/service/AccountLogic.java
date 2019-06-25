package com.sk.sample.mall.account.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sk.sample.mall.account.domain.model.Account;
import com.sk.sample.mall.account.domain.repository.AccountRepository;

@Service("accountLogic")
public class AccountLogic implements AccountService {
	@Autowired
	private AccountRepository accountRepository;

	@Override
	@Transactional(readOnly=true)
	public Account findById(Long id) {
		return accountRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Account> findByNameLike(String name) {
		return accountRepository.findByNameLike(name);
	}

	@Override
	@Transactional(readOnly=true)
	public Account findByEmail(String email) {
		return accountRepository.findByEmail(email);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	
	@Override
	@Transactional(readOnly=true)
	public Page<Account> findAll(Pageable pageable) {
		return accountRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public Account register(Account account) {
		return accountRepository.save(account);
	}

	@Override
	@Transactional
	public Account update(Long id, Account newAccount) {
		Account oldAccount = accountRepository.findOne(id);
		if(oldAccount != null) {
			BeanUtils.copyProperties(newAccount,  oldAccount, "id");
			return accountRepository.save(oldAccount);
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public void delete(Long id) {
		accountRepository.delete(id);
	}
}
