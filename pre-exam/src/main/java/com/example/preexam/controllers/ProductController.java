package com.example.preexam.controllers;


import com.example.preexam.entities.Product;
import com.example.preexam.repositories.ProductRepository;
import com.example.preexam.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService service;
    @GetMapping("")
    public ResponseEntity<Object> findAllProducts(
            @RequestParam(defaultValue = "0") Double upper,
            @RequestParam(defaultValue = "0") Double lower,
            @RequestParam(defaultValue = "") String partOfProductName
    ) {
        // If both upper and lower bounds are less than 0, return all products based on the part of product name
            // Otherwise, proceed with the normal logic
            return ResponseEntity.ok(service.findAllProducts(upper, lower, partOfProductName));

    }

    @GetMapping("/{productLine}")
    public List<Product> getAllProductByProductLine(@PathVariable String productLine) {
        return service.findAllProductLineByProduct(productLine);
    }


}
