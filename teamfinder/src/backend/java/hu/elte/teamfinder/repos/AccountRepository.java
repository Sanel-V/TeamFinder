package hu.elte.teamfinder.repos;

import hu.elte.teamfinder.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findAccountModelByEmail(String email);
    // selectAccountByEmail(String username);
    /*
    Optional<Account> selectAccountByEmail(String email);

    Optional<Account> getAccountById(Integer accountId);

    List<Account> getAllAccounts();

    Account addAccount(Account account);

    void deleteAccount(); //Todo: delete profile as well*/
}
