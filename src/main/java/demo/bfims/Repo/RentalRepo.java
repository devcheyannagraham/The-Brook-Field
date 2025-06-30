package demo.bfims.Repo;

import demo.bfims.Entities.Order.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RentalRepo extends JpaRepository<Rental, Long> {
}
