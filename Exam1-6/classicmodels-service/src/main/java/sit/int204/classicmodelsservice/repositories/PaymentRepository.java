package sit.int204.classicmodelsservice.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.classicmodelsservice.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
