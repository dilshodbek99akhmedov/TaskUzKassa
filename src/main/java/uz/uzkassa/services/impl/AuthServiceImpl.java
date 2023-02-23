package uz.uzkassa.services.impl;

import org.springframework.stereotype.Service;
import uz.uzkassa.dtos.auth.LoginDto;
import uz.uzkassa.dtos.auth.RegisterDto;
import uz.uzkassa.services.AuthService;

/**
 * @author Dilshodbek Akhmedov, Thu 10:45 PM. 2/23/23
 */

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String register(RegisterDto dto) {
        return null;

    }

    @Override
    public void confirmation(String token) {

    }

    @Override
    public String login(LoginDto dto) {

        return null;
    }
}
