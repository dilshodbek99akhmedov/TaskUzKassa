package uz.uzkassa.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.uzkassa.dto.user.CreateUserDto;
import uz.uzkassa.dto.user.UpdateUserDto;
import uz.uzkassa.entity.Company;
import uz.uzkassa.entity.User;
import uz.uzkassa.enums.Role;
import uz.uzkassa.enums.Status;
import uz.uzkassa.repository.CompanyRepository;
import uz.uzkassa.repository.UserRepository;
import uz.uzkassa.service.UserService;
import uz.uzkassa.utils.SessionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Dilshodbek Akhmedov, Thu 10:28 PM. 2/23/23
 */

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Override
    public User create(CreateUserDto dto) {
        if (userRepository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail()).isPresent())
            throw new RuntimeException("User already exists");

        Optional<Company> companyOptional = companyRepository.findByIdAndCreatedBy(dto.getCompanyId(), SessionUtils.getSessionId());
        if (!companyOptional.isPresent())
            throw new RuntimeException("Company not found");

        return userRepository.save(
                User.builder()
                        .username(dto.getUsername())
                        .email(dto.getEmail())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .role(Role.EMPLOYEE)
                        .status(Status.CREATED)
                        .company(companyOptional.get())
                        .build()
        );
    }

    @Transactional
    @Override
    public User edit(UpdateUserDto dto) {
        User user = get(dto.getId());
        return userRepository.save(updateUserParams(dto, user));
    }

    private User updateUserParams(UpdateUserDto dto, User user) {
        if (Objects.nonNull(dto.getUsername()) && !dto.getUsername().isEmpty())
            user.setUsername(dto.getUsername());

        if (Objects.nonNull(dto.getEmail()) && !dto.getEmail().isEmpty())
            user.setEmail(dto.getEmail());

        if (Objects.nonNull(dto.getPassword()) && !dto.getPassword().isEmpty())
            user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return user;
    }

    @Transactional
    @Override
    public String block(Long id) {
        User user = get(id);
        user.setStatus(Status.BLOCK);
        Long blockUserID = userRepository.save(user).getId();
        return "User blocked id = " + blockUserID;
    }

    @Override
    public List<User> getAll(Long companyId) {
        return userRepository.getCompanyEmployees(companyId, SessionUtils.getSessionId());
    }

    @Override
    public User get(Long id) {
        Optional<User> userOptional = userRepository.getUserById(id, SessionUtils.getSessionId());
        if (!userOptional.isPresent())
            throw new RuntimeException("User not found");

        return userOptional.get();
    }

}
