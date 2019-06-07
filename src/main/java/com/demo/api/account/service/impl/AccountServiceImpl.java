package com.demo.api.account.service.impl;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.api.account.entity.Account;
import com.demo.api.account.repository.AccountRepository;
import com.demo.api.account.service.AccountService;
import com.demo.api.account.viewhelper.AccountUtil;
import com.demo.api.account.vo.AccountVO;

@Service
public class AccountServiceImpl implements AccountService {
	Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account getAccountById(String id) {
		return accountRepository.findOne(id);
	}

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public String delete(Account account) {
		accountRepository.delete(account);
		return "delete successfully";
	}

	@Override
	public AccountVO getAccountsByCustomerId(String customeId) {
		List<Account> accounts = accountRepository.findAccountByCustomerId(customeId);
		AccountVO accountVo = AccountUtil.generteAccountVO(accounts);
		return accountVo;
	}

	@Override
	public List<Account> getAccountsByDate(String customeId, String from, String to) {
		Date fromDate = Date.valueOf(from);
		Date toDate = Date.valueOf(to);
		List<Account> accounts = accountRepository.findAccountByDate(customeId, fromDate, toDate);

		return accounts;
	}

}
