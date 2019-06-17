package com.demo.api.account.service;

import org.springframework.stereotype.Service;

import com.demo.api.account.entity.Account;
import com.demo.api.account.vo.AccountVO;
import com.demo.api.account.vo.ChartVO;

@Service
public interface AccountService {
	public Account getAccountById(String id);

	public Account save(Account account);

	public String delete(int id);

	public AccountVO getAccountsByCustomerId(String customerId);

	public ChartVO getChartItems(String customerId,int number,String accountType,String dateType);
	
}
