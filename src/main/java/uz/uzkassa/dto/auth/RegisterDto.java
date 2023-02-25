package uz.uzkassa.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Dilshodbek Akhmedov, Thu 10:58 PM. 2/23/23
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterDto {
    @NotBlank(message = "Username is blank")
    private String username;

    @NotBlank(message = "Password is blank")
    private String password;

    @Email(message = "Email is invalid")
    private String email;
}
