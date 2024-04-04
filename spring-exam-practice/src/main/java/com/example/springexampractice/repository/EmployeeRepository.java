package com.example.springexampractice.repository;

import com.example.springexampractice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsById(Integer id);
}
