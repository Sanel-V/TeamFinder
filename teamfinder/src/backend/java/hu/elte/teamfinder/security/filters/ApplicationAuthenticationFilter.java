package hu.elte.teamfinder.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.elte.teamfinder.models.AccountDetails;
import hu.elte.teamfinder.services.AccountService;
import hu.elte.teamfinder.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.Assert;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ApplicationAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    // TODO: Get from secure location
    // private byte[] SECRET = "secret".getBytes();
    private JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;

    @Autowired
    public ApplicationAuthenticationFilter(
            AuthenticationManager authenticationManager, AccountService accountService) {
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult)
            throws IOException, ServletException {
        // AccountDetails account = new
        // AccountDetails(accountService.getAccountByEmail(authResult.getPrincipal().toString()));
        AccountDetails account = (AccountDetails) authResult.getPrincipal();
        Assert.notNull(account.getAccountId(), "Principal returned null account ID");
        // Algorithm algorithm = Algorithm.HMAC256(SECRET);
        this.jwtUtil = new JwtUtil(accountService);
        String access_token = jwtUtil.generateAccessToken(request, account);
        String refresh_token = jwtUtil.generateRefreshToken(request, account);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
