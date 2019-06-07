package com.demo.api.account.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "account")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date createDay;
	private Time createTime;
	private String type;
	private String amount;
	private String customerId;

	@ManyToOne
	private Category category;

}
