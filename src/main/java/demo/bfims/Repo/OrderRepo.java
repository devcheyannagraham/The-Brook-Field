package demo.bfims.Repo;

import demo.bfims.Entities.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    Optional<List<Order>> findOrdersByOrderDateAfter(LocalDateTime orderDate);
    Optional<List<Order>> findOrdersByOrderDateAfterAndCustomerId(LocalDateTime orderDate, long id);
}
