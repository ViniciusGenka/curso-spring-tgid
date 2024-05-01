package com.genka.resources;

import com.genka.domain.Category;
import com.genka.dtos.CategoryNewDTO;
import com.genka.services.CategoryService;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @PostMapping()
    public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryNewDTO categoryNewDTO) {
        Category savedCategory = categoryService.saveCategory(categoryService.mapFromDTO(categoryNewDTO));
        return ResponseEntity.status(HttpStatus.OK).body(savedCategory);
    }
}
