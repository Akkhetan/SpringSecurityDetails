package com.example.ss_action20.controllers;

import com.example.ss_action20.entities.Product;
import com.example.ss_action20.repositories.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    static List<Product> products;
    static {

        products = new ArrayList<>();

        products.add(new Product("p1", "nikolai"));
        products.add(new Product("p2", "nikolai"));
        products.add(new Product("p3", "julien"));
    }

    //Pre-Filter
    @GetMapping("/save")
    public List<Product> saveByOwner() {
        return productRepository.saveByOwner(products);
    }

    //Post-Filter
    @GetMapping("/find")
    public List<Product> findByOwner() {
        return productRepository.findAll();
    }
}