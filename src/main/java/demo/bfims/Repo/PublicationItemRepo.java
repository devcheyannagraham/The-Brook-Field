package demo.bfims.Repo;

import demo.bfims.Entities.Inventory.Publication.PublicationItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationItemRepo extends JpaRepository<PublicationItem, Long> {
    List<PublicationItem> findPublicationItemsByPublication_PublicationId(Long publicationId);

    Integer countPublicationItemsByPublication_publicationId(Long publicationId);
}
