package demo.bfims.Repo;

import demo.bfims.Entities.Inventory.Publication.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepo extends JpaRepository<Publication, Long> {
    Integer deletePublicationByPublicationId(Long id);
}
