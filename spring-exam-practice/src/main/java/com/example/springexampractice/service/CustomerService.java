package com.example.springexampractice.service;

import com.example.springexampractice.model.dto.CustomerDTO;
import com.example.springexampractice.model.dto.OrderDTO;
import com.example.springexampractice.model.dto.PageDTO;
import com.example.springexampractice.repository.CustomerRepository;
import com.example.springexampractice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    public final ModelMapper modelMapper;
    public PageDTO<CustomerDTO> getCustomers(Integer page, Integer size) {
        if (page == -1 && size == -1) {
            List<CustomerDTO> customers = customerRepository.findAll()
                    .stream()
                    .map((element) -> modelMapper.map(element, CustomerDTO.class))
                    .toList();
            return PageDTO.<CustomerDTO>builder()
                    .content(customers)
                    .build();
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerDTO> customerDTOPage = customerRepository.findAll(pageable)
                .map(customer -> modelMapper.map(customer, CustomerDTO.class));
        return PageDTO.<CustomerDTO>builder()
                .content(customerDTOPage.getContent())
                .pageable(customerDTOPage.getPageable())
                .totalPages(customerDTOPage.getTotalPages())
                .totalElements(customerDTOPage.getTotalElements())
                .size(customerDTOPage.getSize())
                .isLast(customerDTOPage.isLast())
                .isFirst(customerDTOPage.isFirst())
                .build();
    }

    public List<OrderDTO> getOrders(Integer id) {
        return orderRepository.findAllByCustomerNumberId(id).stream()
                .map((element) -> modelMapper.map(element, OrderDTO.class))
                .toList();
    }
}
