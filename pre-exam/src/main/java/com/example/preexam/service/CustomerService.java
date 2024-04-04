package com.example.preexam.service;

import com.example.preexam.entities.Customer;
import com.example.preexam.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // เรียกดึงข้อมูลลูกค้าทั้งหมด
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // เรียกดึงข้อมูลลูกค้าด้วยรหัสลูกค้า
    public Customer getCustomerById(int id) {
        try {
            return customerRepository.findById(id).orElseThrow();
        } catch (NumberFormatException e) {
            // Handle if the provided id cannot be parsed into an integer
            System.err.println("Invalid id format: " + e.getMessage());
            return null;
        } catch (NoSuchElementException e) {
            // Handle if customer with the provided id does not exist
            System.err.println("Customer with id " + id + " not found: " + e.getMessage());
            return null;
        } catch (Exception e) {
            // Handle other exceptions
            System.err.println("Error fetching customer: " + e.getMessage());
            return null;
        }
    }

    // บันทึกข้อมูลลูกค้า
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // ลบข้อมูลลูกค้าด้วยรหัสลูกค้า
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(int id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            customer.setCustomerNumber(id);
            return customerRepository.save(customer);
        }
        return null;
    }
}
