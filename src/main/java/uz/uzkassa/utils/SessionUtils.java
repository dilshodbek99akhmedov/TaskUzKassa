package uz.uzkassa.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import uz.uzkassa.dto.data.Principal;

public class SessionUtils {

    public static Long getSessionId() {
        return ((Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    public static boolean sessionHasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().
                filter(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_" + role))
                .count() == 1;
    }

    public static boolean sessionHasAnyRole(String... roles) {
        for (String role : roles) {
            if (sessionHasRole(role)) {
                return true;
            }
        }
        return false;
    }
}
