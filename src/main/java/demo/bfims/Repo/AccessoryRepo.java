package demo.bfims.Repo;

import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Publication.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AccessoryRepo extends JpaRepository<Accessory, Long>{
    Integer deleteByAccessoryId(Long id);
    List<Accessory> getAccessoriesByAccessoryNameContaining(String name);
}
