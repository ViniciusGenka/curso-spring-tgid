package com.genka.services;

import com.genka.domain.product.Category;
import com.genka.dtos.CategoryNewDTO;
import com.genka.repositories.CategoryRepository;
import com.genka.resources.exceptions.DataIntegrityException;
import com.genka.resources.exceptions.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Page<Category> findAllPaginatedCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " not found"));
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        getCategoryById(category.getId());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Integer categoryId) {
        Category categoryToDelete = getCategoryById(categoryId);
        try {
            categoryRepository.delete(categoryToDelete);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Category cannot be deleted if there are products linked to it");
        }
    }

    public Category mapFromDTO(CategoryNewDTO categoryNewDTO) {
        return new Category(
                categoryNewDTO.getName()
        );
    }
}
