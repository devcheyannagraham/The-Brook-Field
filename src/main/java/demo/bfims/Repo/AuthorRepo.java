package demo.bfims.Repo;

import demo.bfims.Entities.Inventory.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {
}
