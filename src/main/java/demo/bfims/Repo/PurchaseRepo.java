package demo.bfims.Repo;

import demo.bfims.Entities.Orders.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {
}
