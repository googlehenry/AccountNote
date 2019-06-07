package com.demo.api.account.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.api.account.entity.Account;
import com.demo.api.account.vo.AccountVO;

@Service
public interface AccountService {
	public Account getAccountById(String id);

	public Account save(Account account);

	public String delete(Account account);

	public AccountVO getAccountsByCustomerId(String customerId);

	public List<Account> getAccountsByDate(String customerId, String from, String to);
}
