package uz.uzkassa.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 * @author Dilshodbek Akhmedov, Fri 10:49 AM. 2/24/23
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionDto implements Serializable {
    private Long accessTokenExpiry;
    private Long refreshTokenExpiry;
    private Long issuedAt;
    private String accessToken;
    private String refreshToken;
}
