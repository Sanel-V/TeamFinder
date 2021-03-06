package hu.elte.teamfinder.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import hu.elte.teamfinder.models.AccountDetails;
import hu.elte.teamfinder.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtUtil {
    public static final String HEADER_PREFIX = "Bearer: ";
    // 10 minutes
    public static final Integer ACCESS_EXPIRATION_TIME = (1000 * 60 * 10);
    // 30 days
    public static final Integer REFRESH_EXPIRATION_TIME = (1000 * 60 * 60 * 24 * 30);
    // TODO: secure secret
    private static final byte[] SECRET = "secret".getBytes();

    private Algorithm algorithm;

    private final AccountService accountService;

    @Autowired
    public JwtUtil(AccountService accountService) {
        this.accountService = accountService;
        this.algorithm = Algorithm.HMAC256(SECRET);
    }

    public String generateAccessToken(HttpServletRequest request, AccountDetails account) {
        return JWT.create()
                .withSubject(account.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .withIssuer(request.getRequestURI())
                .withClaim("id", account.getAccountId())
                .withClaim(
                        "authorities",
                        account.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .sign(algorithm);
    }

    public String generateRefreshToken(HttpServletRequest request, AccountDetails account) {
        return JWT.create()
                .withSubject(account.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .withIssuer(request.getRequestURI())
                .sign(algorithm);
    }

    public DecodedJWT extractJwtTokenFromHeader(String authorizationHeader) {
        // get token without "Bearer: " prefix
        String token = authorizationHeader.substring(JwtUtil.HEADER_PREFIX.length());

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

    public Set<SimpleGrantedAuthority> extractAuthoritiesFromToken(DecodedJWT token) {
        Set<SimpleGrantedAuthority> authorities =
                Arrays.stream(token.getClaim("authorities").asArray(String.class))
                        .map(authority -> new SimpleGrantedAuthority(authority))
                        .collect(Collectors.toSet());

        if (authorities == null) {
            throw new NullPointerException("No 'authorities' claim found in token");
        }
        return authorities;
    }

    public UsernamePasswordAuthenticationToken authorizeToken(String authorizationHeader) {
        DecodedJWT decodedJWT = this.extractJwtTokenFromHeader(authorizationHeader);
        String username = decodedJWT.getSubject();
        Map<String, Claim> claims = decodedJWT.getClaims();
        String id = claims.get("id").asString();
        String[] authoritiesArr = claims.get("authorities").asArray(String.class);
        Set<SimpleGrantedAuthority> authorities = this.extractAuthoritiesFromToken(decodedJWT);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        accountService.loadUserByUsername(username), null, authorities);
        return authenticationToken;
    }
}
