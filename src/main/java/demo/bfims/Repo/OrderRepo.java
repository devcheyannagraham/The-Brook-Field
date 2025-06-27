package demo.bfims.Repo;

import demo.bfims.Entities.Orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders,Long> {
}
