package hu.elte.teamfinder.services;

import hu.elte.teamfinder.models.AccountModel;
import hu.elte.teamfinder.models.AccountUserDetails;
import hu.elte.teamfinder.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        Optional<AccountModel> account = accountRepository.findAccountModelByEmail(username);
        account.orElseThrow(
                () -> new UsernameNotFoundException(String.format("User %s not found", username)));

        return new AccountUserDetails(account.get());
    }

    public List<AccountModel> getAllAccounts() {
        return accountRepository.findAll();
    }

    public AccountModel getAccountById(Integer id) {
        return accountRepository.getById(id);
    }

    public AccountModel getAccountByEmail(String email) {
        return accountRepository.findAccountModelByEmail(email).get();
    }

    public AccountModel addAccount(AccountModel account) {
        return accountRepository.save(account);
    }
    /*
        public AccountModel updateAccount(AccountModel account)
        {
            //If account already exists, updates
            return accountRepository.save(account);
        }
    */
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }
}
