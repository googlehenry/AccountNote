package com.demo.api.account.vo;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Item{
	private String itemId;
	private String createDay;
	private String createTime;
	private String imagePath;
	private int categoryCode;
	private String categoryDesc;
	private String type;
	private String amount;
}
