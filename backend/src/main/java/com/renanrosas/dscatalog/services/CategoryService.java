package com.renanrosas.dscatalog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renanrosas.dscatalog.dto.CategoryDTO;
import com.renanrosas.dscatalog.entities.Category;
import com.renanrosas.dscatalog.repositories.CategoryRepository;
import com.renanrosas.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {
  
  private final CategoryRepository categoryRepository;
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Transactional(readOnly = true)
  public List<CategoryDTO> findAll() {
    // This method will return all categories;
    List<Category> list = categoryRepository.findAll();
    return list.stream().map(CategoryDTO::new).toList();
  }

  @Transactional(readOnly = true)
  public CategoryDTO findById(Long id) {
    // This method will return a category by its ID;
    Optional<Category> obj = categoryRepository.findById(id);
    Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    return new CategoryDTO(entity);
  }

  @Transactional
  public CategoryDTO insert(CategoryDTO dto) {
    // This method will insert a new category;
    Category entity = new Category();
    entity.setName(dto.getName());
    entity = categoryRepository.save(entity);
    return new CategoryDTO(entity);
  }
}
