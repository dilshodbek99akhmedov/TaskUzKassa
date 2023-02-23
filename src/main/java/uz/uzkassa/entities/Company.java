package uz.uzkassa.entities;

import lombok.*;

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
    private String zipCode;

    @OneToMany(
            mappedBy = "company",
            fetch = FetchType.EAGER
    )
    private List<User> users;
}
