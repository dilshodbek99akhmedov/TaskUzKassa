package uz.uzkassa.config.securty;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Dilshodbek Akhmedov, Fri 9:51 AM. 2/24/23
 */

public class JwtUtils {
    public static Integer expiry = 12 * 60 * 60_000;
    public static String secret = "ASDQW#@!$#@%$#DSFSDFRT%$#%34543terg45%^%$";

    public static Date getExpiry() {
        return new Date(System.currentTimeMillis() + expiry);
    }

    public static Date getExpiryForRefreshToken() {
        return new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(30));
    }

    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret.getBytes());
    }

    public static JWTVerifier getVerifier() {
        return JWT.require(getAlgorithm()).build();
    }
}
