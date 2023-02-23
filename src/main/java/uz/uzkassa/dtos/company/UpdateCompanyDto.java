package uz.uzkassa.dtos.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Dilshodbek Akhmedov, Fri 12:48 AM. 2/24/23
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompanyDto {
    private Long id;
    private String name;
    private String address;
    private String zipCode;
}
