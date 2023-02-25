package uz.uzkassa.service;

import uz.uzkassa.dto.auth.RegisterDto;

/**
 * @author Dilshodbek Akhmedov, Thu 10:45 PM. 2/23/23
 */
public interface AuthService {

    /**
     * @param dto
     * @return
     */
    String register(RegisterDto dto);

    /**
     * @param token
     * @return
     */
    String confirmation(String token);
}
