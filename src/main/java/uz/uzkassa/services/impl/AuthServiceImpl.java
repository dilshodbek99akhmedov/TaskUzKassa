package uz.uzkassa.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.uzkassa.dtos.auth.LoginDto;
import uz.uzkassa.dtos.auth.RegisterDto;
import uz.uzkassa.services.AuthService;
import uz.uzkassa.utils.EmailUtils;

import java.util.Random;
import java.util.UUID;

/**
 * @author Dilshodbek Akhmedov, Thu 10:45 PM. 2/23/23
 */

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final EmailUtils emailUtils;

    @Override
    public String register(RegisterDto dto) {
        String token = UUID.randomUUID().toString();
        emailUtils.sendAsyncMessage(dto.getEmail(), token);
        return null;
    }

    @Override
    public String confirmation(String token) {
        return null;
    }

    @Override
    public String login(LoginDto dto) {

        return null;
    }
}
