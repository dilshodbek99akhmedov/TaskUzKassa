package uz.uzkassa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.uzkassa.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Dilshodbek Akhmedov, Thu 10:29 PM. 2/23/23
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(
            value = "select * from users u where u.id = ?1 and" +
                    " u.company_id in (select c.id from company c where c.created_by = ?2)",
            nativeQuery = true
    )
    Optional<User> findByUsernameAndOwner(Long id, Long ownerId);

    @Query(
            value = "select * from users u where u.company_id = ?1 and" +
                    " u.company_id in (select c.id from company c where c.created_by = ?2)",
            nativeQuery = true
    )
    List<User> findByCompanyEmployeesAndOwner(Long id, Long ownerId);

    Optional<User> findByUsernameOrEmail(String username, String email);

    @Modifying
    @Query(value = "update users set status = ?2 where email = ?1", nativeQuery = true)
    void activatedUserStatus(String email, String active);
}
