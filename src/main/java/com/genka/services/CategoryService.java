package com.genka.services;

import com.genka.domain.Category;
import com.genka.dtos.CategoryNewDTO;
import com.genka.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> findCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " not found"));
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category mapFromDTO(CategoryNewDTO categoryNewDTO) {
        return new Category(
                categoryNewDTO.getId(),
                categoryNewDTO.getName()
        );
    }
}
