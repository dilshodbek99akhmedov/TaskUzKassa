package uz.uzkassa.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author Dilshodbek Akhmedov, Fri 7:31 AM. 2/24/23
 */

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email_settings")
public class EmailSettings extends Auditable {
    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private String email;

    @CreationTimestamp
    @Column(
            name = "created_at",
            columnDefinition = "timestamp default now()"
    )
    private LocalDateTime createdAt;

}
