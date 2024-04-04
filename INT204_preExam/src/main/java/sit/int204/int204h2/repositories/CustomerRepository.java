package sit.int204.int204h2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.int204h2.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
