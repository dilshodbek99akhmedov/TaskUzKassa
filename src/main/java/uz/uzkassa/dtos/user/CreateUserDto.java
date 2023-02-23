package uz.uzkassa.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.uzkassa.enums.Role;

/**
 * @author Dilshodbek Akhmedov, Fri 12:46 AM. 2/24/23
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    private String username;
    private String password;
    private String email;
    private Role role;
}
