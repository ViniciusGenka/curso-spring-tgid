package com.genka.resources;

import com.genka.domain.product.Category;
import com.genka.dtos.CategoryDTO;
import com.genka.dtos.CategoryNewDTO;
import com.genka.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping()
    public ResponseEntity<Page<CategoryDTO>> findAllPaginatedCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy));
        Page<CategoryDTO> categories = categoryService.findAllPaginatedCategories(pageable).map(CategoryDTO::new);
        return ResponseEntity.status(HttpStatus.OK).body(categories);
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
