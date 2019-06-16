package com.demo.api.account.viewhelper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.demo.api.account.entity.Category;

public class CategoryUtil {
	public static Map<String,List<Category>> groupCategory(List<Category> categories){
		Map<String,List<Category>> categoryMap =categories.stream().collect(Collectors.groupingBy(Category::getType));
		return categoryMap;
	}
}
