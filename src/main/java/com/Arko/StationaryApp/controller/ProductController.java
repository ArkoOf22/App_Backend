package com.Arko.StationaryApp.controller;

import com.Arko.StationaryApp.model.Product;
import com.Arko.StationaryApp.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    //Get all the products
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    //Add a product
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return ResponseEntity.ok("Product added successfully");
    }

    //Get product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product=productService.getAllProducts()
                .stream().filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Product not found !!!"));
        return ResponseEntity.ok(product);
    }

    //Update product details
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct){
        Product product1=productService.getAllProducts()
                .stream().filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Product not found !!!"));

        product1.setName(updatedProduct.getName());
        product1.setDescription(updatedProduct.getDescription());
        product1.setPrice(updatedProduct.getPrice());
        product1.setStockQuantity(updatedProduct.getStockQuantity());
        productService.saveProduct(product1);
        return ResponseEntity.ok("Product updated successfully");
    }
}
