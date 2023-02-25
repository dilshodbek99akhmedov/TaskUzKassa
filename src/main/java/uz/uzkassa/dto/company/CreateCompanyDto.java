package uz.uzkassa.dto.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Dilshodbek Akhmedov, Thu 11:29 PM. 2/23/23
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyDto {
    @NotBlank(message = "Company name is blank")
    private String name;

    @NotBlank(message = "Address is blank")
    private String address;

    @NotBlank(message = "ZipCode is blank")
    private String zipCode;
}
