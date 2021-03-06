package hu.elte.teamfinder.services;

import hu.elte.teamfinder.exceptions.UserAlreadyExists;
import hu.elte.teamfinder.models.Account;
import hu.elte.teamfinder.models.AccountDetails;
import hu.elte.teamfinder.models.Profile;
import hu.elte.teamfinder.repos.AccountRepository;
import hu.elte.teamfinder.repos.ProfileRepository;
import hu.elte.teamfinder.security.AccountRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService implements UserDetailsService {

    private final ProfileRepository profileRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(
            ProfileRepository profileRepository,
            AccountRepository accountRepository,
            PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // From UserDetailsService
    @Override
    public AccountDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Treat emails as usernames
        Optional<Account> account = accountRepository.findAccountByEmail(username);
        account.orElseThrow(
                () -> new UsernameNotFoundException(String.format("User %s not found", username)));

        return new AccountDetails(account.get());
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.getById(id);
    }

    public Account getAccountByEmail(String email) {
        return accountRepository
                .findAccountByEmail(email)
                .orElseThrow(
                        () ->
                                new UsernameNotFoundException(
                                        String.format("User %s not found", email)));
    }

    public Account addAccount(Account account) throws UserAlreadyExists {
        if (account.getEmail() == null || account.getEmail() == "") {
            throw new IllegalArgumentException("Invalid email");
        }
        try {
            getAccountByEmail(account.getEmail());
        } catch (UsernameNotFoundException e1) {
            // No account exists with this email, we can safely create one
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            if (account.getProfile() == null) {
                account.setProfile(new Profile(account));
            }
            profileRepository.save(account.getProfile());
            return accountRepository.save(account);
        }
        throw new UserAlreadyExists("Account with email " + account.getEmail() + " already exists");
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public Account updateAccount(Long id, Account account) throws UserAlreadyExists {
        Account account1;
        Account accountToUpdate = getAccountById(id);

        // check if email is in use
        try {
            account1 = getAccountByEmail(account.getEmail());
        } catch (UsernameNotFoundException e1) {
            // No account exists with this email, we can safely update
            if (account.getPassword() != "") {
                accountToUpdate.setPassword(passwordEncoder.encode(account.getPassword()));
            }
            accountToUpdate.setEmail(account.getEmail());
            return accountRepository.save(accountToUpdate);
        }
        // Email belongs to account that we're updating
        if (account1.getAccountId() == accountToUpdate.getAccountId()) {
            accountToUpdate.setPassword(passwordEncoder.encode(account.getPassword()));
            return accountRepository.save(accountToUpdate);
        }
        throw new UserAlreadyExists("Account with email " + account.getEmail() + " already exists");
    }

    public Account addAccountRoles(Long id, Set<AccountRole> roles) {
        Account accountToUpdate = getAccountById(id);
        if (accountToUpdate != null) {
            accountToUpdate.getRoles().addAll(roles);
            return accountRepository.save(accountToUpdate);
        } else {
            throw new UsernameNotFoundException("Invalid account ID");
        }
    }

    public Account removeAccountRoles(Long id, HashSet<AccountRole> roles) {
        Account accountToUpdate = getAccountById(id);
        if (accountToUpdate != null) {
            accountToUpdate.getRoles().removeAll(roles);
            return accountRepository.save(accountToUpdate);
        } else {
            throw new UsernameNotFoundException("Invalid account ID");
        }
    }
}
