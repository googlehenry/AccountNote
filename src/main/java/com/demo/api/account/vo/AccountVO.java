package com.demo.api.account.vo;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AccountVO {
	private AccountSummary accountSummary;
	private Map<String,List<Item>> item;
}




