package com.genka.services;

import com.genka.domain.product.Category;
import com.genka.domain.product.Product;
import com.genka.dtos.ProductNewDTO;
import com.genka.repositories.CategoryRepository;
import com.genka.repositories.ProductRepository;
import com.genka.resources.exceptions.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Product getProductById(Integer productId) {
        return productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " not found"));
    }

    public Page<Product> search(String name, List<Integer> categoryIds, Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction), orderBy);
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        return productRepository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product mapFromDTO(ProductNewDTO productNewDTO) {
        List<Category> categories = new ArrayList<>();
        for (Integer categoryId : productNewDTO.getCategoryIds()) {
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("Category with id " + categoryId + " not found"));
            categories.add(category);
        }
        return new Product(
                productNewDTO.getName(),
                productNewDTO.getPrice(),
                categories
        );
    }
}
