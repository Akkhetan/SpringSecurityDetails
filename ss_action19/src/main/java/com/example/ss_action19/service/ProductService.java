package com.example.ss_action19.service;

import com.example.ss_action19.model.Product;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private static  List<Product> returnedProducts;

    static {
         returnedProducts = new ArrayList<>();

        returnedProducts.add(new Product("tea", "nikolai"));
        returnedProducts.add(new Product("coffee", "nikolai"));
        returnedProducts.add(new Product("lemon", "julien"));
    }


    /*
    #  You use prefiltering/postfiltering only if the method receives as a parameter an array or a collection of objects.
       The framework filters this collection or array according to rules you define.
    #  With filterObject, we refer to objects in the list as parameters(In this case List<Product> products).
    #  For the @PreFilter and @PostFilter annotations, we can directly refer to the authentication object, which is available in the SecurityContext
       after authentication
     */
    @PreFilter("filterObject.owner == authentication.name")
    public List<Product> sellProducts(List<Product> products) {
        // sell products and return the sold products list
        return products;
    }

    @PostFilter("filterObject.owner == authentication.principal.username")
    public List<Product> findProducts() {
        return returnedProducts;
    }
}