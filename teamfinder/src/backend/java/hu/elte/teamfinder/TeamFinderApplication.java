package hu.elte.teamfinder;

import hu.elte.teamfinder.repos.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TeamFinderApplication {

  public static void main(String[] args) {
    SpringApplication.run(TeamFinderApplication.class, args);
  }

  @Bean
  CommandLineRunner run(AccountRepository repository) {
    return args -> {
      // repository.save(new AccountModel(1,"Bob", "25"));

    };
  }
}
