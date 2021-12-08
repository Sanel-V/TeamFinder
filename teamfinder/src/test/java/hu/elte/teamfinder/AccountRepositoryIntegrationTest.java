package hu.elte.teamfinder;

import hu.elte.teamfinder.models.Account;
import hu.elte.teamfinder.repos.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryIntegrationTest {

    @Autowired private TestEntityManager entityManager;

    @Autowired private AccountRepository accountRepository;

    public AccountRepositoryIntegrationTest() {}

    @Test
    public void getAccountById() {
        Account account = new Account("example@gmail.com", "pass");
        Long accountId = account.getAccountId();
        entityManager.persist(account);
        entityManager.flush();

        Account found = accountRepository.getById(accountId);

        assertThat(found.getEmail()).isEqualTo(account.getEmail());
    }
}
