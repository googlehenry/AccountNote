package com.demo.api.account.controller;

import java.util.List;

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
import com.demo.api.account.vo.AccountVO;

@RestController
@RequestMapping("/v1/esc/")
public class AccountController {
	Logger log = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	AccountService accountService;

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
	
	@GetMapping("/items")
	public Response getAccountByDate(@RequestParam("userId") String userId,
									@RequestParam("from") String from,
									@RequestParam("to") String to) {
		List<Account> accounts = accountService.getAccountsByDate(userId, from, to);
		return new Response(AccountConstants.OK, accounts);
	}

	@PostMapping("/accountToSave")
	public Response save(@RequestBody Account account) {
		log.debug(account.toString());
		Account accountSaved = accountService.save(account);
		return new Response(AccountConstants.CREATED, accountSaved);
	}

	@DeleteMapping("/accountToDelete")
	public Response delete(@RequestBody Account account) {
		String msg = accountService.delete(account);
		return new Response(AccountConstants.OK, msg);
	}

}
