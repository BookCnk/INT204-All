package sit.int204.int204h2.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sit.int204.int204h2.dtos.PageDto;
import sit.int204.int204h2.dtos.SimpleCustomerDto;
import sit.int204.int204h2.entities.Customer;
import sit.int204.int204h2.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ListMapper listMapper;

    public PageDto<SimpleCustomerDto> getAllCustomer(int pageNo, int pageSize, String[] sortBy, String[] sortDirection) {
        List<Sort.Order> sortOrder = new ArrayList<>();
        if (sortBy != null && sortBy.length != 0) {
            for (int i = 0; i < sortBy.length; i++) {
                sortOrder.add(new Sort.Order(Sort.Direction.valueOf(sortDirection[i].toUpperCase()), sortBy[i]));
            }
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortOrder));
        Page<Customer> customerPage = repository.findAll(pageable);
        Page<SimpleCustomerDto> simpleCustomerDtoPage = listMapper.mapListPage(customerPage, SimpleCustomerDto.class, modelMapper);
        return modelMapper.map(simpleCustomerDtoPage, PageDto.class);
    }
}
