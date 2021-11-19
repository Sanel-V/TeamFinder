package hu.elte.teamfinder.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true) //TODO: uncomment this when controllers are
// configured with @PreAuthorize
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    /*
        @Autowired
        private final PasswordEncoder passwordEncoder;

        public ApplicationSecurityConfig(PasswordEncoder passwordEncoder)
        {
            this.passwordEncoder = passwordEncoder;
        }
    */
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
