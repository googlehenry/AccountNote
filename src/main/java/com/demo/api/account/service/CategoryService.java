package com.demo.api.account.service;

import java.util.List;
import java.util.Map;

import com.demo.api.account.entity.Category;

public interface CategoryService {
	public Map<String, List<Category>> getAllCategories();

	Category getCategoryById(int code);

}
