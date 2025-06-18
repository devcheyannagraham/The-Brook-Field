package demo.bfims.Repo;

import demo.bfims.Entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepo extends JpaRepository<Publication,Long> {
}
