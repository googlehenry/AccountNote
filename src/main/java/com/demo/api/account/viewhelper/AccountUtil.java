package com.demo.api.account.viewhelper;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.api.account.entity.Account;
import com.demo.api.account.vo.AccountSummary;
import com.demo.api.account.vo.AccountVO;
import com.demo.api.account.vo.Item;


public class AccountUtil {
	static Logger log = LoggerFactory.getLogger(AccountUtil.class);
	public static AccountVO generteAccountVO(List<Account> accounts) {
		Double outcome = accounts.stream().filter(a -> a.getType().equals("out"))
				.map(account -> new Double(account.getAmount())).reduce((double) 0, (a, b) -> a + b);
		Double income = accounts.stream().filter(a -> a.getType().equals("in"))
				.map(account -> new Double(account.getAmount())).reduce((double) 0, (a, b) -> a + b);
		
		SimpleDateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormatter=new SimpleDateFormat("hh:mm:ss");
		
		@SuppressWarnings("unchecked")
		Map<String,List<Item>> items = (Map) accounts.stream()
				.map(a -> new Item(String.valueOf(a.getId()), dateFormatter.format(a.getCreateDay()), timeFormatter.format(a.getCreateTime()), a.getCategory().getImagePath(),
						a.getCategory().getCode(), a.getCategory().getDescription(), a.getType(), a.getAmount()))
				.collect(Collectors.groupingBy(Item::getCreateDay));

		log.info("outcome: " + outcome.toString());
		log.info("income: " + income.toString());
		log.info("items: " + items);

		AccountSummary accountSummary = new AccountSummary(String.valueOf(outcome), String.valueOf(income));
		AccountVO accountVo = new AccountVO(accountSummary, items);
		return accountVo;
	}

}
