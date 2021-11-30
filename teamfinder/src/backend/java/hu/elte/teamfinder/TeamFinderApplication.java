package hu.elte.teamfinder;

import hu.elte.teamfinder.models.Account;
import hu.elte.teamfinder.repos.AccountRepository;
import hu.elte.teamfinder.security.AccountRole;
import hu.elte.teamfinder.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class TeamFinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamFinderApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(AccountService service) {
        return args -> {
            service.addAccount(new Account("Bob", "pass"));
        };
    }
}
