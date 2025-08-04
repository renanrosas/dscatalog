package com.renanrosas.dscatalog.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renanrosas.dscatalog.entities.Category;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  @GetMapping
  public ResponseEntity<List<Category>> findAll() {
    // This method will return all categories;
    List<Category> list = new ArrayList<>();
    list.add(new Category(1L, "Books"));
    list.add(new Category(2L, "Electronics"));
    return ResponseEntity.ok().body(list);
  }
}
