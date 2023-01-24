package com.renanrosas.dscatalog.services;

import com.renanrosas.dscatalog.entities.Category;
import com.renanrosas.dscatalog.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }
}
