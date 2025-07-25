package demo.bfims.Repo;

import demo.bfims.Entities.Inventory.Accessory.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessoryRepo extends JpaRepository<Accessory, Long>{
    Integer deleteByAccessoryId(Long id);
    List<Accessory> findByQuantityLessThanEqual(Integer quantity);

}
