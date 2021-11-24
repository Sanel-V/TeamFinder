package hu.elte.teamfinder.security;

import hu.elte.teamfinder.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true) //TODO: uncomment this when controllers are
// configured with @PreAuthorize
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;

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

        http.cors()
                .and()
                .csrf()
                .disable() // TODO: consider removing this in live build
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll()
        // .antMatchers("/accounts/**").hasAnyRole("ADMIN") //ROLE_ADMIN
        // .anyRequest()
        // .authenticated()
        // .and()
        // .formLogin()
        // .defaultSuccessUrl("/index.html",true)
        ;
    }
}
