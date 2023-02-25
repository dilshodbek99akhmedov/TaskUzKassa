package uz.uzkassa.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import uz.uzkassa.enums.Role;
import uz.uzkassa.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Dilshodbek Akhmedov, Thu 10:19 PM. 2/23/23
 */

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends Auditable {
    @Column(
            unique = true,
            nullable = false
    )
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(
            nullable = false,
            name = "user_role"
    )
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @CreatedDate
    @CreationTimestamp
    @Column(
            name = "created_at",
            columnDefinition = "timestamp default now()"
    )
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

}
