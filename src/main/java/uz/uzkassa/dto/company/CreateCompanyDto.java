package uz.uzkassa.dto.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Dilshodbek Akhmedov, Thu 11:29 PM. 2/23/23
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyDto {
    private String name;
    private String address;
    private String zipCode;
}
