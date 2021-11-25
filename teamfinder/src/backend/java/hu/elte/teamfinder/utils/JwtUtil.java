package hu.elte.teamfinder.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import hu.elte.teamfinder.models.AccountDetails;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtUtil {
    public static final String HEADER_PREFIX = "Bearer: ";
    // 10 minutes
    public static final Integer ACCESS_EXPIRATION_TIME = (1000 * 60 * 10);
    // 30 days
    public static final Integer REFRESH_EXPIRATION_TIME = (1000 * 60 * 60 * 24 * 30);

    private Algorithm algorithm;

    public JwtUtil(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String generateAccessToken(HttpServletRequest request, AccountDetails account) {
        return JWT.create()
                .withSubject(account.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .withIssuer(request.getRequestURI().toString())
                .withClaim("id", account.getAccountId())
                .withClaim(
                        "roles",
                        account.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .sign(algorithm);
    }

    public String generateRefreshToken(HttpServletRequest request, AccountDetails account) {
        return JWT.create()
                .withSubject(account.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .withIssuer(request.getRequestURI().toString())
                .sign(algorithm);
    }
}
