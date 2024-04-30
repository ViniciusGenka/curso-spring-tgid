package com.genka.resources;

import com.genka.domain.Category;
import com.genka.dtos.CategoryDTO;
import com.genka.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    private final CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.status(200).body(category);
    }

    @PostMapping()
    public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category categoryToSave = categoryService.mapFromDTO(categoryDTO);
        Category savedCategory = categoryService.saveCategory(categoryToSave);
        return ResponseEntity.status(200).body(savedCategory);
    }
}
