package hu.elte.teamfinder.services;

import hu.elte.teamfinder.models.AccountModel;
import hu.elte.teamfinder.models.AccountUserDetails;
import hu.elte.teamfinder.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountDetailsService implements UserDetailsService
{
    private final AccountRepository accountRepository;

    //Switch implementation of repo by switching qualifier value
    @Autowired
    public AccountDetailsService(@Qualifier("fake") AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    //NOTE: We use the email address as the username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        Optional<AccountModel> account = accountRepository.selectAccountByEmail(username);
        account.orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));

        return new AccountUserDetails(account.get());
    }

    public UserDetails loadUserById(Integer accountId) throws UsernameNotFoundException
    {
        Optional<AccountModel> account = accountRepository.getAccountById(accountId);
        account.orElseThrow(() -> new UsernameNotFoundException(String.format("User with id: %d not found", accountId)));

        return new AccountUserDetails(account.get());
    }
}
