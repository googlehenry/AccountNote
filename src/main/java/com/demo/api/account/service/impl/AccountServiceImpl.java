package com.demo.api.account.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.api.account.AccountConstants;
import com.demo.api.account.entity.Account;
import com.demo.api.account.repository.AccountRepository;
import com.demo.api.account.service.AccountService;
import com.demo.api.account.viewhelper.AccountUtil;
import com.demo.api.account.viewhelper.DateUtil;
import com.demo.api.account.vo.AccountVO;
import com.demo.api.account.vo.ChartVO;
import com.demo.api.account.vo.Duration;

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
		Sort sort = new Sort(Sort.Direction.DESC, "createDay");
		List<Account> accounts = accountRepository.findAccountLikeBySort(customeId, sort);
		for (Account u : accounts) {
			log.info(new SimpleDateFormat("yyyy-MM-dd").format(u.getCreateDay()));
		}
		AccountVO accountVo = AccountUtil.generteAccountVO(accounts);
		return accountVo;
	}

	@Override
	public ChartVO getChartItems(String customeId, int number, String accountType,String dateType) {
		ChartVO chartVo=new ChartVO();
		
		List<Account> accounts=new ArrayList<Account>();
		if(AccountConstants.DATE_TYPE_YEAR.equals(dateType)){
			accounts = accountRepository.findAccountByCustomerId(customeId);
			chartVo = AccountUtil.generateYearChartVO(accounts,accountType);
		}else {
			Duration duration=DateUtil.getDuration(dateType, number);
			accounts = accountRepository.findAccountByDate(customeId, duration.getFrom(), duration.getTo());
			chartVo = AccountUtil.generateMonthWeekChartVO(accounts,accountType,duration);
		}
		
		return chartVo;
	}
	
	

}
