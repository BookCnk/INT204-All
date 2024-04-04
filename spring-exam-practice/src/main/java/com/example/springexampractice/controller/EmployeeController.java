package com.example.springexampractice.controller;

import com.example.springexampractice.model.Employee;
import com.example.springexampractice.model.dto.EmployeeDTO;
import com.example.springexampractice.model.dto.PageDTO;
import com.example.springexampractice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<PageDTO<Employee>> getAll(@RequestParam(defaultValue = "") String sortDirection,
                                                    @RequestParam(defaultValue = "") String sortBy,
                                                    @RequestParam(defaultValue = "-1") int page,
                                                    @RequestParam(defaultValue = "-1") int size) {
        return ResponseEntity.ok(employeeService.findAllPaginated(sortBy, sortDirection, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<Employee> add(@RequestBody EmployeeDTO employee) {
        return ResponseEntity.ok(employeeService.add(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@RequestBody Employee employee, @PathVariable Long id) {
        return ResponseEntity.ok(employeeService.update(employee, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
