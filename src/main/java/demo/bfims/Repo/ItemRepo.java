package demo.bfims.Repo;

import demo.bfims.Entities.Inventory.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item,Long> {
}
