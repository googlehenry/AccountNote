package com.demo.api.account.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Code;
	private String Description;
	private String imagePath;
	private String type;

}
