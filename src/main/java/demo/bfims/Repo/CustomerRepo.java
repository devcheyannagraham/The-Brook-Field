package demo.bfims.Repo;

import demo.bfims.Entities.Order.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    Optional<Customer> getCustomerByEmail(String email);
}
