package com.example.preexam.service;

import com.example.preexam.entities.Product;
import com.example.preexam.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;


    public List<Product> findAllProductLineByProduct(String productLine) {
        return repository.findByProductLineStartingWith(productLine);
    }

    public Page<Product> findAllProducts(Double upper, Double lower, String partOfProductName, String[] sortBy, String[] direction, int pageNo, int pageSize) {
        if (upper < lower) {
            double tmp = upper;
            upper = lower;
            lower = tmp;
        }
        if (upper == 0 && lower == 0) {
            upper = repository.findFirstByOrderByBuyPriceDesc().getPrice();
        }
        List<Sort.Order> sortOrders = new ArrayList<>();
        if (sortBy != null & sortBy.length > 0) {
            for (int i = 0; i < sortBy.length; i++) {
                sortOrders.add(new Sort.Order((direction[i].equalsIgnoreCase("asc") ?
                        Sort.Direction.ASC : Sort.Direction.DESC), sortBy[i]));
            }
        }

        if (pageSize <= 0) {
            pageSize = (int) repository.count();
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortOrders));
        return repository.findByPriceBetweenAndProductLineContainsIgnoreCase(lower, upper, partOfProductName, pageable);
    }


    public List<Product> findAllProducts(Double upper, Double lower, String partOfProductName) {
        if (upper < lower) {
            double tmp = upper;
            upper = lower;
            lower = tmp;
        }
        if (upper == 0 && lower == 0) {
            return repository.findByProductLineStartingWith(partOfProductName);
//            upper = repository.findFirstByOrderByBuyPriceDesc().getPrice();
        }
        return repository.findByPriceBetweenAndProductLineContainsIgnoreCase(lower, upper, partOfProductName);
    }
}
