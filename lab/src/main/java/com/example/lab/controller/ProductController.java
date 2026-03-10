package com.example.lab.controller;

import com.example.lab.exception.MissingApiKeyException;
import com.example.lab.model.Product;
import com.example.lab.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {
    private final ProductService productService;
    private static final String API_KEY_VALUE = "123456";
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<Product> create(@RequestHeader(value = "API-Key", required = false) String apiKey, @Valid @RequestBody Product product){
        checkApiKey(apiKey);
        Product created=productService.create(product.getName(),product.getPrice(),product.getCategory(),product.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping
    public List<Product> getAll(@RequestHeader(value = "API-Key", required = false) String apiKey){
        checkApiKey(apiKey);
        return productService.findAll();
    }
    @GetMapping("{name}")
    public Product getByName(@RequestHeader(value = "API-Key", required = false) String apiKey,@PathVariable String name){
        checkApiKey(apiKey);
        return productService.findByName(name);
    }
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@RequestHeader(value = "API-Key", required = false) String apiKey,@PathVariable String category){
        checkApiKey(apiKey);
        return productService.findByCategory(category);
    }
    @GetMapping("/price")
    public List<Product> getByRange(@RequestHeader(value = "API-Key", required = false) String apiKey,@RequestParam Double min,@RequestParam Double max){
        checkApiKey(apiKey);
        return productService.findByRange(min,max);
    }
    @PutMapping("{name}")
    public Product fullUpdate(@RequestHeader(value = "API-Key", required = false) String apiKey,@PathVariable String name,@Valid @RequestBody Product product){
        checkApiKey(apiKey);
        return productService.fullUpdate(product.getName(),product.getPrice(), product.getCategory(), product.getQuantity());
    }
    @PatchMapping("{name}")
    public Product partialUpdate(@RequestHeader(value = "API-Key", required = false) String apiKey,@PathVariable String name, @RequestBody Product product){
        checkApiKey(apiKey);
        return productService.partialUpdate(product.getName(),product.getPrice(), product.getCategory(), product.getQuantity());
    }
    @DeleteMapping("{name}")
    public ResponseEntity<Void> delete(@RequestHeader(value = "API-Key", required = false) String apiKey,@PathVariable String name){
        checkApiKey(apiKey);
        productService.delete(name);
        return ResponseEntity.noContent().build();
    }
    private void checkApiKey(String apiKey){
        if(apiKey == null){
            throw new MissingApiKeyException("Missing API-Key header");
        }
        if(!apiKey.equals(API_KEY_VALUE)){
            throw new MissingApiKeyException("Invalid API-Key");
        }
    }

}