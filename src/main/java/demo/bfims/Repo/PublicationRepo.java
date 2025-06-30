package demo.bfims.Repo;

import demo.bfims.Entities.Inventory.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepo extends JpaRepository<Publication,Long> {
}
