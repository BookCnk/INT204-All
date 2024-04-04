package com.example.springexampractice.model.dto;

import com.example.springexampractice.model.Product;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
    String productCode;
    String productName;
    String productLine;
    String productVendor;
    String productDescription;
    Integer quantityInStock;
    Double price;
}