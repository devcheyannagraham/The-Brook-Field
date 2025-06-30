package demo.bfims.Repo;

import demo.bfims.Entities.Order.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {
}
