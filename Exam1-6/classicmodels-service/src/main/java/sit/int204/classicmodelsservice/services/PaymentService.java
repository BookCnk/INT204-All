package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;
import sit.int204.classicmodelsservice.entities.Payment;
import sit.int204.classicmodelsservice.repositories.PaymentRepository;

import java.util.List;

@Service


public class PaymentService {
    @Autowired PaymentRepository repositories;

    public List<Payment> findAllPayments(){
        return repositories.findAll();
    }

}
