package com.renanrosas.dscatalog.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renanrosas.dscatalog.dto.CategoryDTO;
import com.renanrosas.dscatalog.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService categoryService;
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<List<CategoryDTO>> findAll() {
    // This method will return all categories;
    List<CategoryDTO> list = categoryService.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
    // This method will return a category by its ID;
    CategoryDTO dto = categoryService.findById(id);
    return ResponseEntity.ok().body(dto);
  }
}
