package com.renanrosas.dscatalog.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renanrosas.dscatalog.entities.Category;
import com.renanrosas.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
  
  private final CategoryRepository categoryRepository;
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Transactional(readOnly = true)
  public List<Category> findAll() {
    // This method will return all categories;
    return categoryRepository.findAll();
  }
}
