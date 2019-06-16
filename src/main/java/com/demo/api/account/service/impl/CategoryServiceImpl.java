package com.demo.api.account.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.api.account.entity.Category;
import com.demo.api.account.repository.CategoryRepository;
import com.demo.api.account.service.CategoryService;
import com.demo.api.account.viewhelper.AccountUtil;
import com.demo.api.account.viewhelper.CategoryUtil;

@Service
public class CategoryServiceImpl implements CategoryService {
	static Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Map<String, List<Category>> getAllCategories() {
		try {
			List<Category> categories = (List<Category>) categoryRepository.findAll();
			Map<String, List<Category>> categoryMap = CategoryUtil.groupCategory(categories);
			return categoryMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Category getCategoryById(int code) {
		log.info("categoryRepository: "+categoryRepository+"**");
		log.info("code: "+code+"**");
		try {
			Category category = (Category) categoryRepository.findOne(code);
			return category;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
