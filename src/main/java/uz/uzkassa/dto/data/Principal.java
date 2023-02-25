package uz.uzkassa.dto.data;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Dilshodbek Akhmedov, Fri 2:35 PM. 2/24/23
 */

@Getter
@Setter
public class Principal {
    private Long id;
    private String username;
    private String[] roles;
    private String status;

    public Principal(Long id, String username, String[] roles, String status) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.status = status;
    }
}
