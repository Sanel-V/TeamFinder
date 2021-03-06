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
                new ApplicationAuthenticationFilter(
                        authenticationManagerBean(), this.accountService);
        myAuthFilter.setFilterProcessesUrl("/api/login");
        http.headers().frameOptions().disable();
        http.cors()
                .and()
                .csrf()
                .disable() // TODO: consider removing this in live build
                .authorizeRequests()
                .antMatchers("/api/login/**", "account/token/refresh", "/account/add/**", "/h2-console/**")
                .permitAll()
                .antMatchers("/profile/public/**")
                .hasAnyAuthority("profile:read")
                .antMatchers("/profile/all/**")
                .hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/profile/update/**")
                .hasAnyAuthority("profile:write")
                .antMatchers("/profile/get/**")
                .hasAnyAuthority("profile:read")
                .antMatchers("/account/get/**")
                .hasAnyAuthority("account:read")
                .antMatchers("/account/update/**")
                .hasAnyAuthority("account:write")
                .antMatchers("/account/delete/**")
                .hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/account/all/**")
                .hasAnyAuthority("ROLE_ADMIN", "account:read")
                .anyRequest()
                .authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(myAuthFilter);
        this.jwtUtil = new JwtUtil(accountService);
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
