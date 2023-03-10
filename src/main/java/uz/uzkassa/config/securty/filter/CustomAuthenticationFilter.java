package uz.uzkassa.config.securty.filter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.uzkassa.config.securty.CustomUserDetails;
import uz.uzkassa.config.securty.JwtUtils;
import uz.uzkassa.dto.auth.LoginDto;
import uz.uzkassa.dto.auth.SessionDto;
import uz.uzkassa.dto.data.AppErrorDto;
import uz.uzkassa.dto.data.DataDto;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author Dilshodbek Akhmedov, Fri 9:58 AM. 2/24/23
 */

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/api/v1/auth/sign-in");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginDto loginDto = new ObjectMapper().readValue(request.getReader(), LoginDto.class);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            log.error("Exception", e);
            throw new RuntimeException("Exception", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws ServletException, IOException {
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        Date expiryForAccessToken = JwtUtils.getExpiry();
        Date expiryForRefreshToken = JwtUtils.getExpiryForRefreshToken();

        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expiryForAccessToken)
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities()
                        .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withClaim("id", user.getId())
                .withClaim("status", user.getStatus().name())
                .sign(JwtUtils.getAlgorithm());

        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(expiryForRefreshToken)
                .withIssuer(request.getRequestURL().toString())
                .sign(JwtUtils.getAlgorithm());

        SessionDto sessionDto = SessionDto.builder()
                .accessToken(accessToken)
                .accessTokenExpiry(expiryForAccessToken.getTime())
                .refreshToken(refreshToken)
                .refreshTokenExpiry(expiryForRefreshToken.getTime())
                .issuedAt(System.currentTimeMillis())
                .build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), new DataDto<>(sessionDto));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws ServletException, IOException {
        DataDto<AppErrorDto> resp = getCustomErrorMessage(request, failed);
        new ObjectMapper().writeValue(response.getOutputStream(), resp);
    }

    private DataDto<AppErrorDto> getCustomErrorMessage(HttpServletRequest request, AuthenticationException failed) {
        String message;
        if (failed.getMessage().equalsIgnoreCase("User is disabled"))
            message = "You may be blocked or not activated, check your email";
        else
            message = failed.getMessage();

        return new DataDto<>(
                AppErrorDto.builder()
                        .message(message)
                        .path(request.getRequestURL().toString())
                        .status(HttpStatus.FORBIDDEN)
                        .build()
        );
    }

}
