package com.genka.resources;

import com.genka.domain.product.Product;
import com.genka.dtos.ProductDTO;
import com.genka.dtos.ProductNewDTO;
import com.genka.resources.utils.URL;
import com.genka.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

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
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value="name", defaultValue="") String name,
            @RequestParam(value="categoryIds", defaultValue="") String categoryIdsString,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="size", defaultValue="24") Integer size,
            @RequestParam(value="orderBy", defaultValue="name") String orderBy,
            @RequestParam(value="direction", defaultValue="asc") String direction) {
        String nameDecoded = URL.decodeParam(name);
        List<Integer> categoryIdsList = URL.decodeIntList(categoryIdsString);
        Page<Product> productEntities = productService.search(nameDecoded, categoryIdsList, page, size, orderBy, direction);
        Page<ProductDTO> productDtos = productEntities.map(ProductDTO::new);
        return ResponseEntity.ok().body(productDtos);
    }

    @PostMapping()
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductNewDTO productNewDTO) {
        Product savedProduct = productService.saveProduct(productService.mapFromDTO(productNewDTO));
        return ResponseEntity.status(HttpStatus.OK).body(savedProduct);
    }
}
