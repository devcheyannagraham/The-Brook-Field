package demo.bfims.Repo;

import demo.bfims.Entities.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepo extends JpaRepository<Journal,Long> {
}
