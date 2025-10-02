package demo.bfims.Repo;

import demo.bfims.Entities.Order.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> getCustomerByEmail(String email);
    Optional<Customer> getCustomerById(Long id);
}
