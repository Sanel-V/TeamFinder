package hu.elte.teamfinder.services;

import hu.elte.teamfinder.repos.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AccountService implements UserDetailsService
{
    //TODO: implement accountRepository
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    //NOTE: We use the email address as the username
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
       return accountRepository.selectAccountUserDetailsByEmail(email)
               .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", email)));
    }
}
