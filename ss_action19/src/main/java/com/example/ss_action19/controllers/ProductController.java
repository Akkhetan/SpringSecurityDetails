package com.example.ss_action19.controllers;

import com.example.ss_action19.model.Product;
import com.example.ss_action19.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    static List<Product> products;
    static {

        /*
        Note :  aspect changes the given collection. It’s the same instance from which the aspect removed the elements that didn’t match the given criteria. This is important to take into consideration. You must always make sure that the collection instance you provide is not immutable. Providing an immutable collection to be processed results in an exception at execution time because the filtering aspect won’t be able to change the collection’s contents.
            products = new ArrayList<>(); // Mutable instance
             products = List.of() //Immutable instance
         */

//        products = List.of(new Product("beer", "nikolai"),
//                new Product("candy", "nikolai"),
//                new Product("chocolate", "julien"));

        products = new ArrayList<>();

        products.add(new Product("beer", "nikolai"));
        products.add(new Product("candy", "nikolai"));
        products.add(new Product("chocolate", "julien"));
    }

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Example for Pre-filtering
    @GetMapping("/sell")
    public List<Product> sellProduct() {
        return productService.sellProducts(ProductController.products);
    }

    // Example for Post-filtering
    @GetMapping("/find")
    public List<Product> findProducts() {
        return productService.findProducts();
    }
}