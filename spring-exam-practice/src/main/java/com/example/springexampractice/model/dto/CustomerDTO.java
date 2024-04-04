package com.example.springexampractice.model.dto;

import com.example.springexampractice.model.Customer;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Customer}
 */
@Data
public class CustomerDTO implements Serializable {
    Integer id;
    String customerName;
    String contactLastName;
    String contactFirstName;
    String phone;
    String state;
    String postalCode;
    List<OrderDTO> orders;
}