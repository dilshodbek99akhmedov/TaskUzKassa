package uz.uzkassa.helper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.uzkassa.dto.data.Principal;
import uz.uzkassa.entity.Company;
import uz.uzkassa.entity.EmailSettings;
import uz.uzkassa.entity.User;
import uz.uzkassa.enums.Role;
import uz.uzkassa.enums.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Dilshodbek Akhmedov, Thu 10:33 AM. 2/26/23
 */

public class DataHelper {

    public static List<Company> getCompaniesToIntialize() {
        Company companyDtoOne = new Company(
                "Company 1",
                "Tashkent 1",
                "1111111",
                Status.ACTIVE,
                1L,
                new ArrayList<>());
        companyDtoOne.setId(1L);

        Company companyDtoTwo = new Company(
                "Company 2",
                "Tashkent 2",
                "2222222",
                Status.ACTIVE,
                1L,
                new ArrayList<>());
        companyDtoTwo.setId(2L);
        return Arrays.asList(companyDtoOne, companyDtoTwo);
    }

    public static List<User> getUsersToIntialize() {
        User user2 = User.builder()
                .username("user 1")
                .password("111")
                .email("dilshooodbek@gmail.com")
                .role(Role.EMPLOYEE)
                .company(null)
                .status(Status.CREATED)
                .build();
        user2.setId(2L);

        User user1 = User.builder()
                .username("admin")
                .password("123")
                .email("dilshodbeka404@gmail.com")
                .role(Role.OWNER)
                .company(null)
                .status(Status.ACTIVE)
                .build();
        user1.setId(1L);
        return Arrays.asList(user1, user2);
    }

    public static User getTestUser() {
        return new User(
                "u",
                "1",
                "test@gmail.com",
                Role.EMPLOYEE,
                Status.ACTIVE,
                LocalDateTime.now(),
                null,
                null);
    }

    public static EmailSettings getEmailSettingsToIntialize() {
        return EmailSettings.builder()
                .token("email-settings-token-89235892")
                .email("dilshodbeka404@gmail.com")
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static void setSecurityContextHolder() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_OWNER"));
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        new Principal(1L, "admin", new String[]{"ROLE_OWNER"}, Status.ACTIVE.name()),
                        null,
                        authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

}
