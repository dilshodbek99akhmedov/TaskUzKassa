package uz.uzkassa.service;

import uz.uzkassa.dto.auth.RegisterDto;

/**
 * @author Dilshodbek Akhmedov, Thu 10:45 PM. 2/23/23
 */
public interface AuthService {

    /**
     * @param dto Parameters required to save the user to the system
     * @return If successfully saved in the user's system, a confirmation message will be sent to his email.
     * This is a message about the need to check the user's email.
     */
    String register(RegisterDto dto);

    /**
     * @param token Activation token in the mail
     * @return Message about activation of the user
     */
    String confirmation(String token);
}
