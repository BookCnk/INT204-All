package com.example.preexam.repositories;

import com.example.preexam.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String > {
    List<Product> findByPriceBetweenAndProductLineContainsIgnoreCase(Double lower, Double upper, String partOfName);

    List<Product> findByProductLineStartingWith(String productLine);

    Product findFirstByOrderByBuyPriceDesc();

    List<Product> findByProductNameContains(String name);

}
