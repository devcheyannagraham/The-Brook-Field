package demo.bfims.Repo;

import demo.bfims.Entities.Inventory.Publication.PublicationItem;
import demo.bfims.Enums.PublicationItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationItemRepo extends JpaRepository<PublicationItem, Long> {
    List<PublicationItem> findPublicationItemsByPublication_PublicationId(Long publicationId);

    List<PublicationItem> findPublicationItemByPublication_PublicationIdAndPublicationItemStatus(Long publicationId, PublicationItemStatus publicationItemStatus);

    Integer countPublicationItemsByPublication_publicationIdAndPublicationItemStatus(Long publicationId,  PublicationItemStatus publicationItemStatus);
}
