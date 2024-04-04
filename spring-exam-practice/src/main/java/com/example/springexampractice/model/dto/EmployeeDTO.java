package com.example.springexampractice.model.dto;

import com.example.springexampractice.model.Employee;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Employee}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO implements Serializable {
    Integer id;
    String lastName;
    String firstName;
    String extension;
    String email;
    String officeCode;
    String jobTitle;
}