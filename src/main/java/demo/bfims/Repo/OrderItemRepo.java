package demo.bfims.Repo;

import demo.bfims.Entities.Order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}