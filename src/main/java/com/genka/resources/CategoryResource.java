package com.genka.resources;

import com.genka.domain.product.Category;
import com.genka.dtos.CategoryDTO;
import com.genka.dtos.CategoryNewDTO;
import com.genka.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> findAllCategories() {
        List<Category> categories = categoryService.findAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories.stream().map(CategoryDTO::new).collect(Collectors.toList()));
    }

    @PostMapping()
    public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryNewDTO categoryNewDTO) {
        Category savedCategory = categoryService.saveCategory(categoryService.mapFromDTO(categoryNewDTO));
        return ResponseEntity.status(HttpStatus.OK).body(savedCategory);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody CategoryNewDTO categoryUpdateDTO, @PathVariable Integer id) {
        Category categoryToUpdate = categoryService.mapFromDTO(categoryUpdateDTO);
        categoryToUpdate.setId(id);
        Category savedCategory = categoryService.updateCategory(categoryToUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(savedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
