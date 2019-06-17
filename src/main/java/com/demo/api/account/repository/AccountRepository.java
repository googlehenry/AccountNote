package com.demo.api.account.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.demo.api.account.entity.Account;

public interface AccountRepository extends CrudRepository<Account, String> {

	@Query("select account from Account account where account.customerId = ?1")
	public List<Account> findAccountLikeBySort(String customerId,Sort sort);
	
	@Query("select account from Account account where account.customerId = ?1")
	public List<Account> findAccountByCustomerId(String customerId);

	@Query("select account from Account account where account.customerId = ?1 and account.createDay>=?2 and account.createDay<=?3")
	public List<Account> findAccountByDate(String customerId, Date from, Date to);
	
	@Transactional
	public void deleteById(int id);
	

}
