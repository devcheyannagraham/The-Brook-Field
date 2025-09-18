package demo.bfims.Repo;

import demo.bfims.Entities.Order.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    Optional<List<Transaction>> findAllTransactionsByTransactionDateAfter(LocalDate date);



}
