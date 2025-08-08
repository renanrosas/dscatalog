package com.renanrosas.dscatalog.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.renanrosas.dscatalog.dto.ProductDTO;
import com.renanrosas.dscatalog.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable){
    // This method will return all categories with pagination;
    Page<ProductDTO> list = productService.findAllPaged(pageable);
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
    // This method will return a product by its ID;
    ProductDTO dto = productService.findById(id);
    return ResponseEntity.ok().body(dto);
  }

  @PostMapping
  public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
    // This method will insert a new product;
    dto = productService.insert(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
    // This method will update an existing product;
    dto = productService.update(id, dto);
    return ResponseEntity.ok().body(dto);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    // This method will delete a product by its ID;
    productService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
