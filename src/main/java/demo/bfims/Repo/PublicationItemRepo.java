package demo.bfims.Repo;

import demo.bfims.Entities.PublicationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationItemRepo extends JpaRepository<PublicationItem, Long> {
}
