package demo.bfims.Repo;

import demo.bfims.Entities.Orders.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepo extends JpaRepository<Rental, Long> {
}
