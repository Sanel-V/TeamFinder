package hu.elte.teamfinder.security;

import hu.elte.teamfinder.security.filters.ApplicationAuthenticationFilter;
import hu.elte.teamfinder.security.filters.ApplicationAuthorizationFilter;
import hu.elte.teamfinder.services.AccountService;
import hu.elte.teamfinder.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.jar.JarEntry;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true) //TODO: uncomment this when controllers are
// configured with @PreAuthorize
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;

    private JwtUtil jwtUtil;

    @Autowired
    public ApplicationSecurityConfig(
            PasswordEncoder passwordEncoder, AccountService accountService) {
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.accountService).passwordEncoder(this.passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ApplicationAuthenticationFilter myAuthFilter =
                new ApplicationAuthenticationFilter(authenticationManagerBean());
        myAuthFilter.setFilterProcessesUrl("/api/login");
        // TODO: fully configure endpoint access permissions
        http.cors()
                .and()
                .csrf()
                .disable() // TODO: consider removing this in live build
                .authorizeRequests()
                .antMatchers("/api/login/**", "account/token/refresh")
                .permitAll()
                .antMatchers("/account/all/**")
                .hasAnyAuthority("ROLE_STANDARD")
                .anyRequest()
                .authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilter(myAuthFilter);
        this.jwtUtil = new JwtUtil();
        http.addFilterBefore(
                new ApplicationAuthorizationFilter(jwtUtil),
                UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
