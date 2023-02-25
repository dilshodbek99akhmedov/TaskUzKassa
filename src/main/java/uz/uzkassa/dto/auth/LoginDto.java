package uz.uzkassa.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Dilshodbek Akhmedov, Thu 10:42 PM. 2/23/23
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDto {
    @NotBlank(message = "Username is blank")
    private String username;

    @NotBlank(message = "Password is blank")
    private String password;
}
