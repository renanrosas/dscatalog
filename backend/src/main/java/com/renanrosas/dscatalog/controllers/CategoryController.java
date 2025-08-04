package com.renanrosas.dscatalog.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renanrosas.dscatalog.entities.Category;
import com.renanrosas.dscatalog.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  private final CategoryService categoryService;
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }


  @GetMapping
  public ResponseEntity<List<Category>> findAll() {
    // This method will return all categories;
    List<Category> list = categoryService.findAll();
    return ResponseEntity.ok().body(list);
  }
}
