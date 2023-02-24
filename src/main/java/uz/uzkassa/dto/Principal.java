package uz.uzkassa.dto;

import lombok.Getter;
import lombok.Setter;
import uz.uzkassa.enums.Status;

/**
 * @author Dilshodbek Akhmedov, Fri 2:35 PM. 2/24/23
 */

@Getter
@Setter
public class Principal {
    private String id;
    private String username;
    private Status status;

    public Principal(String id, String username, String status) {
        this.id = id;
        this.username = username;
        this.status = Status.valueOf(status);
    }
}
