package uz.uzkassa.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.uzkassa.config.securty.UserDetailsConf;
import uz.uzkassa.dtos.auth.LoginDto;
import uz.uzkassa.dtos.auth.RegisterDto;
import uz.uzkassa.entities.User;
import uz.uzkassa.enums.Status;
import uz.uzkassa.repositories.EmailSettingsRepository;
import uz.uzkassa.repositories.UserRepository;
import uz.uzkassa.services.AuthService;
import uz.uzkassa.utils.EmailUtils;

import java.util.UUID;

/**
 * @author Dilshodbek Akhmedov, Thu 10:45 PM. 2/23/23
 */

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private final EmailUtils emailUtils;
    private final UserRepository userRepository;
    private final EmailSettingsRepository emailSettingsRepository;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getStatus().equals(Status.BLOCK))
            throw new RuntimeException("User blocked");

        return new UserDetailsConf(user);
    }
}
