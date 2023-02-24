package uz.uzkassa.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Dilshodbek Akhmedov, Thu 10:03 PM. 2/23/23
 */

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class Auditable {
    @Id
    @Column(unique = true)
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
}
