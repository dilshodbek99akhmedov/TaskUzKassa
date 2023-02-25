package uz.uzkassa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.uzkassa.entity.Company;

import java.util.Optional;

/**
 * @author Dilshodbek Akhmedov, Thu 10:29 PM. 2/23/23
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByIdAndCreatedBy(Long id, Long createdBy);
}
