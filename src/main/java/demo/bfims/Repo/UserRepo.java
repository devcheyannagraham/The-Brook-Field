package demo.bfims.Repo;

import demo.bfims.Entities.Users.User;
import demo.bfims.Enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, byte[] password);
    Optional<User> findByEmail(String email);
    Optional<User> findUserByUserId(Long userId);
    Optional<List<User>> findAllByUserRole(UserRole userRole);
    Integer deleteUserByUserId(Long userId);
}
