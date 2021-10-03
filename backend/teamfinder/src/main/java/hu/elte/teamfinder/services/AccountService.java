package hu.elte.teamfinder.services;

import hu.elte.teamfinder.models.AccountModel;
import hu.elte.teamfinder.models.AccountUserDetails;
import hu.elte.teamfinder.repos.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class AccountService implements UserDetailsService
{
    //TODO: implement accountRepository
    //if we save AccountModel instead of AccountUserDetails we keep the accountId (see TO DO's in AccountUserDetails
    //AccountRepository and this.loadUserByUsername method)
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    //NOTE: We use the email address as the username
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        //TODO: return AccountUserDetails from AccountModel (maybe?)
        /*
        Optional<AccountModel> account = accountRepository.selectAccountUserDetailsByEmail(email);
        account.orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", email)));
        return account.map(acc -> new AccountUserDetails(account,)).get();
        */
        return null;
    }
}
