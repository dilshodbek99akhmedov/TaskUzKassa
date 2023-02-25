package uz.uzkassa.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.uzkassa.config.securty.CustomUserDetails;
import uz.uzkassa.dto.auth.RegisterDto;
import uz.uzkassa.entity.EmailSettings;
import uz.uzkassa.entity.User;
import uz.uzkassa.enums.Role;
import uz.uzkassa.enums.Status;
import uz.uzkassa.repository.EmailSettingsRepository;
import uz.uzkassa.repository.UserRepository;
import uz.uzkassa.service.AuthService;
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
    private final PasswordEncoder passwordEncoder;
    private final EmailSettingsRepository emailSettingsRepository;

    @Override
    public String register(RegisterDto dto) {
        if (userRepository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail()).isPresent())
            throw new RuntimeException("User already exists");

        userRepository.save(
                User.builder()
                        .username(dto.getUsername())
                        .email(dto.getEmail())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .role(Role.OWNER)
                        .status(Status.CREATED)
                        .build()
        );

        sendEmail(dto.getEmail());
        return "A confirmation email has been sent to your email";
    }

    private void sendEmail(String email) {
        String token = UUID.randomUUID().toString();
        emailUtils.sendAsyncMessage(email, token);
        emailSettingsRepository.save(
                EmailSettings.builder()
                        .email(email)
                        .token(token).build()
        );
    }

    @Transactional
    @Override
    public String confirmation(String token) {
        EmailSettings emailSettings = emailSettingsRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        userRepository.activatedUserStatus(emailSettings.getEmail(), Status.ACTIVE.name());
        return "Status activated";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getStatus().equals(Status.CREATED)) {
            sendEmail(user.getEmail());
        }
        return new CustomUserDetails(user);
    }
}
