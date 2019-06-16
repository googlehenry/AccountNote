package com.demo.api.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.account.AccountConstants;
import com.demo.api.account.dto.Response;
import com.demo.api.account.entity.Account;
import com.demo.api.account.service.AccountService;
import com.demo.api.account.service.CategoryService;
import com.demo.api.account.viewhelper.AccountUtil;
import com.demo.api.account.vo.AccountVO;
import com.demo.api.account.vo.ChartVO;
import com.demo.api.account.vo.Item;

@RestController
@RequestMapping("/v1/esc/")
public class AccountController {
	Logger log = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	AccountService accountService;

	@Autowired
	CategoryService categoryService;

	@GetMapping("/accounts/{id}")
	public Response getAccountById(@PathVariable("id") String id) {
		Account account = accountService.getAccountById(id);
		return new Response(AccountConstants.OK, account);
	}

	@GetMapping("/itemsByCustomerId")
	public Response getAccountByUserId(@RequestParam("customerId") String customerId) {
		AccountVO accountVO = accountService.getAccountsByCustomerId(customerId);
		return new Response(AccountConstants.OK, accountVO);
	}
	
	// http://47.102.197.196:1201/v1/esc/chartItems?customerId=123&number=24&accountType=out&dateType=week
	@GetMapping("/chartItems")
	public Response getChartPoints(@RequestParam("customerId") String customerId, 
				@RequestParam("number") int number,
				@RequestParam("accountType") String accountType,
				@RequestParam("dateType") String dateType) {
			ChartVO chartVo = accountService.getChartItems(customerId, number,accountType,dateType);
			return new Response(AccountConstants.OK, chartVo);
		}

	@PostMapping("/{customerId}/account")
	public Response save(@PathVariable String customerId, @RequestBody Item item) {
		log.debug(item.toString());
		Account account = AccountUtil.generateAccount(item, customerId,categoryService);
		Account accountSaved = null;
		if (item != null) {
			accountSaved = accountService.save(account);
		}
		return new Response(AccountConstants.CREATED, accountSaved);
	}

	@DeleteMapping("/accountToDelete")
	public Response delete(@RequestBody Account account) {
		String msg = accountService.delete(account);
		return new Response(AccountConstants.OK, msg);
	}

}
