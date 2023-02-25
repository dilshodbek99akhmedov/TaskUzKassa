package uz.uzkassa.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.uzkassa.enums.Role;

import javax.validation.constraints.NotBlank;

/**
 * @author Dilshodbek Akhmedov, Fri 12:50 AM. 2/24/23
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    @NotBlank(message = "User ID is blank")
    private Long id;
    private String username;
    private String password;
    private String email;
}
