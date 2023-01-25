package com.renanrosas.dscatalog.services;

import com.renanrosas.dscatalog.dto.CategoryDTO;
import com.renanrosas.dscatalog.entities.Category;
import com.renanrosas.dscatalog.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> list = repository.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }
}
