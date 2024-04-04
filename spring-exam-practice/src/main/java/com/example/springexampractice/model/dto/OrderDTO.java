package com.example.springexampractice.model.dto;

import com.example.springexampractice.model.Order;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Order}
 */
@Data
public class OrderDTO implements Serializable {
    Integer id;
    LocalDate orderDate;
    LocalDate requiredDate;
    LocalDate shippedDate;
    String status;
    String comments;
}