package sit.int204.classicmodelsservice.controllers;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.dtos.PageDTO;
import sit.int204.classicmodelsservice.dtos.SimpleCustomerDTO;
import sit.int204.classicmodelsservice.entities.Customer;
import sit.int204.classicmodelsservice.entities.Employee;
import sit.int204.classicmodelsservice.entities.Order;
import sit.int204.classicmodelsservice.services.CustomerService;
import sit.int204.classicmodelsservice.services.ListMapper;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    @GetMapping("")
    public ResponseEntity<Object> getAllCustomers(
            // รับพารามิเตอร์ pageable เพื่อระบุว่าต้องการให้ผลลัพธ์เป็นหน้าหรือไม่ (ค่าเริ่มต้นเป็น false)
            @RequestParam(defaultValue = "false") boolean pageable,
            // รับพารามิเตอร์ page เพื่อระบุหมายเลขหน้า (ค่าเริ่มต้นเป็น 0)
            @RequestParam(defaultValue = "0") int page,
            // รับพารามิเตอร์ pageSize เพื่อระบุขนาดของหน้า (ค่าเริ่มต้นเป็น 10)
            @RequestParam(defaultValue = "10") int pageSize) {
        if(pageable) {
            // หากต้องการให้ผลลัพธ์เป็นหน้า
            // เรียกใช้เมธอด getCustomers จาก service เพื่อดึงข้อมูลผู้ลูกค้า
            Page<Customer> customerPage = service.getCustomers(page, pageSize);
            // แปลงผลลัพธ์เป็นหน้าข้อมูลแบบ DTO ด้วย listMapper และ SimpleCustomerDTO
            return ResponseEntity.ok(listMapper.toPageDTO(customerPage, SimpleCustomerDTO.class));
        } else {
            // หากไม่ต้องการให้ผลลัพธ์เป็นหน้า
            // เรียกใช้เมธอด getCustomers จาก service เพื่อดึงข้อมูลผู้ลูกค้า
            // และแปลงผลลัพธ์เป็นรายการข้อมูลแบบ DTO ด้วย listMapper และ SimpleCustomerDTO
            return ResponseEntity.ok(listMapper.mapList(service.getCustomers(), SimpleCustomerDTO.class));
        }
    }


    @GetMapping("/{id}/orders")
    public List<Order> getCustomerOrder(@PathVariable Integer id) {
        System.out.println("id = "+ id);
        return service.findByID(id).getOrderList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Integer id){
        Customer customer = service.findByID(id);
        SimpleCustomerDTO  simpleCustomerDTO = modelMapper.map(customer, SimpleCustomerDTO.class );
        return ResponseEntity.ok(simpleCustomerDTO);
    }
}
