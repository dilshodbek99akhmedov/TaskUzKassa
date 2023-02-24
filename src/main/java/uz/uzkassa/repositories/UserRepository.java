package uz.uzkassa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.uzkassa.entity.User;

import java.util.Optional;

/**
 * @author Dilshodbek Akhmedov, Thu 10:29 PM. 2/23/23
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
