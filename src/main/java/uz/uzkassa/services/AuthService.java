package uz.uzkassa.services;

import uz.uzkassa.dtos.auth.LoginDto;
import uz.uzkassa.dtos.auth.RegisterDto;

/**
 * @author Dilshodbek Akhmedov, Thu 10:45 PM. 2/23/23
 */
public interface AuthService {
    /**
     * @param dto
     * @return
     */
    String login(LoginDto dto);

    /**
     * @param dto
     * @return
     */
    String register(RegisterDto dto);

    /**
     * @param token
     * @return
     */
    void confirmation(String token);
}
