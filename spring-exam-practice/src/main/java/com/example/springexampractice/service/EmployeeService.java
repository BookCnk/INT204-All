package com.example.springexampractice.service;

import com.example.springexampractice.model.Employee;
import com.example.springexampractice.model.dto.EmployeeDTO;
import com.example.springexampractice.model.dto.PageDTO;
import com.example.springexampractice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public PageDTO<Employee> findAllPaginated(String sortBy, String sortDirection, int page, int size) {
        if (page == -1 && size == -1) {
            return PageDTO.<Employee>builder()
                    .content(employeeRepository.findAll())
                    .build();
        }
        Sort sort = sortBy.isEmpty() ? Sort.unsorted() : Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return PageDTO.<Employee>builder()
                .content(employeePage.getContent())
                .pageable(employeePage.getPageable())
                .build();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Gay")
        );
    }

    public Employee add(EmployeeDTO employee) {
        if (employee.getId() == null) {
            throw new RuntimeException("No id");
        }
        if (employeeRepository.existsById(employee.getId())) {
            throw new RuntimeException("This employee is already exists");
        }
        Employee employeeEntity = modelMapper.map(employee, Employee.class);
        return employeeRepository.save(employeeEntity);
    }

    public Employee update(Employee employee, Long id) {
        if (!Objects.equals(employee.getId(), id)) {
            throw new RuntimeException("Employee id doesn't match");
        }
        if (employeeRepository.existsById(id)) {
            throw new RuntimeException("This employee is already exists");
        }
        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }


}
