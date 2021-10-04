package hu.elte.teamfinder.services;

import hu.elte.teamfinder.models.AccountModel;
import hu.elte.teamfinder.models.AccountUserDetails;
import hu.elte.teamfinder.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

        Optional<AccountModel> account = accountRepository.selectAccountUserDetailsByEmail(username);
        account.orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));

        return account.map(acc -> new AccountUserDetails(account.get(), null)).get();
    }
}
