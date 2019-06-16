package com.demo.api.account.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.api.account.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
