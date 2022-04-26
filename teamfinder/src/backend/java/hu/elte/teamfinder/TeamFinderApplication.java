package hu.elte.teamfinder;

import hu.elte.teamfinder.exceptions.UserAlreadyExists;
import hu.elte.teamfinder.models.Account;
import hu.elte.teamfinder.models.Profile;
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
        // Can't have profile without account and vice-versa
        Account account = new Account("Greg", "pass123");
        Profile test = new Profile(account, "Greg", "asd", 20);
        test.setPublic(true);
        account.setProfile(test);

        return args -> {
            try {
                service.addAccount(new Account("Bob", "pass"));
                service.addAccount(account);
                // WILL THROW EXCEPTION
                // service.addAccount(new Account("Bob", "thebuilder"));

            } catch (UserAlreadyExists userAlreadyExists) {
                userAlreadyExists.printStackTrace();
            }
        };
    }
}
