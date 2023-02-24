package uz.uzkassa.config.securty;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.uzkassa.entities.User;
import uz.uzkassa.enums.Role;
import uz.uzkassa.enums.Status;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dilshodbek Akhmedov, Fri 10:32 AM. 2/24/23
 */

@Getter
public class UserDetailsConf implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private Status status;

    private Set<GrantedAuthority> authorities;

    public UserDetailsConf(User user) {

        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.status = user.getStatus();
        this.authorities = processAuthorities(user.getRole());
    }

    private Set<GrantedAuthority> processAuthorities(Role role) {
        authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status.equals(Status.ACTIVE);
    }
}
