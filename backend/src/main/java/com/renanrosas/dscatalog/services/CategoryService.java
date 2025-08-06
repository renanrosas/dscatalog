package com.renanrosas.dscatalog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renanrosas.dscatalog.dto.CategoryDTO;
import com.renanrosas.dscatalog.entities.Category;
import com.renanrosas.dscatalog.repositories.CategoryRepository;
import com.renanrosas.dscatalog.services.exceptions.DatabaseException;
import com.renanrosas.dscatalog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
    Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
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

  @Transactional
  public CategoryDTO update(Long id, CategoryDTO dto) {
    // This method will update an existing category;
    try {
    Category entity = categoryRepository.getReferenceById(id);
    entity.setName(dto.getName());
    entity = categoryRepository.save(entity);
    return new CategoryDTO(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Id not found " + id);
    }
  }

  public void delete(Long id) {
    if (!categoryRepository.existsById(id)) {
      throw new ResourceNotFoundException("Recurso não encontrado");
    }
    try {
            categoryRepository.deleteById(id);    		
    }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
      }
  }
}
