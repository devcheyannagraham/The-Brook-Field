package demo.bfims.Repo;

import demo.bfims.Entities.Inventory.Accessory.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoryRepo extends JpaRepository<Accessory, Long>{
    Integer deleteByAccessoryId(Long id);
}
