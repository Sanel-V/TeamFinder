package hu.elte.teamfinder.services;

import hu.elte.teamfinder.models.Account;
import hu.elte.teamfinder.models.AccountDetails;
import hu.elte.teamfinder.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // From UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Treat emails as usernames
        Optional<Account> account = accountRepository.findAccountModelByEmail(username);
        account.orElseThrow(
                () -> new UsernameNotFoundException(String.format("User %s not found", username)));

        return new AccountDetails(account.get());
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Integer id) {
        return accountRepository.getById(id);
    }

    public Account getAccountByEmail(String email) {
        return accountRepository
                .findAccountModelByEmail(email)
                .orElseThrow(
                        () ->
                                new UsernameNotFoundException(
                                        String.format("User %s not found", email)));
    }

    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }
    /*
        public Account updateAccount(Account account)
        {
            //If account already exists, updates
            return accountRepository.save(account);
        }
    */
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }
}
