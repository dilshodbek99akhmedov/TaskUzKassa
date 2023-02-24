package uz.uzkassa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.uzkassa.entity.EmailSettings;

/**
 * @author Dilshodbek Akhmedov, Fri 9:27 AM. 2/24/23
 */
public interface EmailSettingsRepository extends JpaRepository<EmailSettings, Long> {
}
