package com.example.ss_action20.repositories;

import com.example.ss_action20.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //Only those product will be saved to DB which are owned by the login user
    @PreFilter("filterObject.owner == authentication.principal.username")
    default List<Product> saveByOwner(Iterable<Product> products){
        return saveAll(products);
    }

    /*
    Using @PostFilter in the repository is inefficient as it filters data after selection. Instead, use SpEL expressions directly in repository queries to fetch only
    required data. Add a SecurityEvaluationContextExtension bean to the Spring context and adjust queries with appropriate selection clauses to optimize performance.
     */
    @Override
    @Query("SELECT p FROM Product p WHERE p.owner=?#{authentication.principal.username}")
   // @PostFilter("filterObject.owner == authentication.principal.username") : this is no efficient
    List<Product> findAll();


}