package uz.uzkassa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.uzkassa.entity.Company;

/**
 * @author Dilshodbek Akhmedov, Thu 10:29 PM. 2/23/23
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
