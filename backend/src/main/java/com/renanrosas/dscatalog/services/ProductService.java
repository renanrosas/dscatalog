package com.renanrosas.dscatalog.services;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renanrosas.dscatalog.dto.CategoryDTO;
import com.renanrosas.dscatalog.dto.ProductDTO;
import com.renanrosas.dscatalog.entities.Category;
import com.renanrosas.dscatalog.entities.Product;
import com.renanrosas.dscatalog.repositories.ProductRepository;
import com.renanrosas.dscatalog.services.exceptions.DatabaseException;
import com.renanrosas.dscatalog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
  
  private final ProductRepository productRepository;

  private final CategoryService categoryService;
  public ProductService(ProductRepository productRepository, CategoryService categoryService) {
    this.productRepository = productRepository;
    this.categoryService = categoryService;
  }

  @Transactional(readOnly = true)
  public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
    // This method will return all categories;
    Page<Product> list = productRepository.findAll(pageRequest);
    return list.map(x -> new ProductDTO(x));
  }

  @Transactional(readOnly = true)
  public ProductDTO findById(Long id) {
    // This method will return a Product by its ID;
    Optional<Product> obj = productRepository.findById(id);
    Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    return new ProductDTO(entity, entity.getCategories());
  }

  @Transactional
  public ProductDTO insert(ProductDTO dto) {
    // This method will insert a new Product;
    Product entity = new Product();
    copyDtoToEntity(dto, entity);
    entity = productRepository.save(entity);
    return new ProductDTO(entity);
  }

  @Transactional
  public ProductDTO update(Long id, ProductDTO dto) {
    // This method will update an existing Product;
    try {
    Product entity = productRepository.getReferenceById(id);
    copyDtoToEntity(dto, entity);
    entity = productRepository.save(entity);
    return new ProductDTO(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Id not found " + id);
    }
  }

  public void delete(Long id) {
    if (!productRepository.existsById(id)) {
      throw new ResourceNotFoundException("Recurso não encontrado");
    }
    try {
            productRepository.deleteById(id);    		
    }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
      }
  }

  private void copyDtoToEntity(ProductDTO dto, Product entity) {
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());
    entity.setPrice(dto.getPrice());
    entity.setImgUrl(dto.getImgUrl());
    entity.setDate(dto.getDate());
    
    entity.getCategories().clear();
    for (CategoryDTO catDto : dto.getCategories()) {
      Category category = categoryService.findById(catDto.getId()).toEntity();
      entity.getCategories().add(category);
    }
  }
}
