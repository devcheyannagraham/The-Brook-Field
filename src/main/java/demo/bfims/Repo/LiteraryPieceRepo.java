package demo.bfims.Repo;

import demo.bfims.Entities.LiteraryPiece;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiteraryPieceRepo extends JpaRepository<LiteraryPiece, Long> {
}
