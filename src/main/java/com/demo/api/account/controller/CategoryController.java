package com.demo.api.account.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.api.account.AccountConstants;
import com.demo.api.account.dto.Response;
import com.demo.api.account.entity.Category;
import com.demo.api.account.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/v1/esc/")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryService;

	@GetMapping("/categories")
	public Response getCategories() {
		Map<String, List<Category>> categoryMap = categoryService.getAllCategories();
		return new Response(AccountConstants.OK, categoryMap);
	}
	
	@GetMapping("/categories/{code}")
	public Response getCategoryById(@PathVariable String code) {
		Category category=categoryService.getCategoryById(Integer.valueOf(code));
		return new Response(AccountConstants.OK, category);
	}
}
