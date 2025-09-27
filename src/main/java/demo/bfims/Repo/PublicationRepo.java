package demo.bfims.Repo;

import demo.bfims.Entities.Inventory.Publication.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationRepo extends JpaRepository<Publication, Long> {
    Integer deletePublicationByPublicationId(Long id);
    Optional<List<Publication>> getPublicationsByTitleContaining(String title);
    Optional<List<Publication>> getPublicationsByAuthor_FirstNameContainingOrAuthor_LastNameContaining(String firstName, String lastName);

}
