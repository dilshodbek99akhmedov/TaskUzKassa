package uz.uzkassa.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uz.uzkassa.enums.Status;

import javax.persistence.*;
import java.util.List;

/**
 * @author Dilshodbek Akhmedov, Thu 10:17 PM. 2/23/23
 */

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company extends Auditable {

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Column(
            name = "zip_code",
            nullable = false
    )
    @JsonProperty("zip_code")
    private String zipCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(
            name = "created_by",
            nullable = false
    )
    @JsonProperty("created_by")
    private Long createdBy;

    @OneToMany(
            mappedBy = "company",
            fetch = FetchType.EAGER
    )
    private List<User> users;
}
