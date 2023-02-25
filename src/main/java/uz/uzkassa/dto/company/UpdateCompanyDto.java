package uz.uzkassa.dto.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Dilshodbek Akhmedov, Fri 12:48 AM. 2/24/23
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompanyDto {
    @NotBlank(message = "Company ID is blank")
    private Long id;
    private String name;
    private String address;
    @JsonProperty("zip_code")
    private String zipCode;
}
