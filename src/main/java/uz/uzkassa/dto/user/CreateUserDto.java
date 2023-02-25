package uz.uzkassa.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Dilshodbek Akhmedov, Fri 12:46 AM. 2/24/23
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    @NotBlank(message = "Username is blank")
    private String username;

    @NotBlank(message = "Password is blank")
    private String password;

    @NotBlank
    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Company ID is blank")
    @JsonProperty("company_id")
    private Long companyId;
}
