package com.genka.resources;

import com.genka.domain.Product;
import com.genka.dtos.ProductNewDTO;
import com.genka.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductResource {
    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.status(200).body(product);
    }

    @PostMapping()
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductNewDTO productNewDTO) {
        Product savedProduct = productService.saveProduct(productService.mapFromDTO(productNewDTO));
        return ResponseEntity.status(200).body(savedProduct);
    }
}
