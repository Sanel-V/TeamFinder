package hu.elte.teamfinder.services;

import hu.elte.teamfinder.models.AccountModel;
import hu.elte.teamfinder.models.AccountUserDetails;
import hu.elte.teamfinder.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountDetailsService implements UserDetailsService {
  private final AccountRepository accountRepository;

  @Autowired
  public AccountDetailsService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  // NOTE: We use the email address as the username
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<AccountModel> account = null; // accountRepository.findAccountModelByEmail(username);
    account.orElseThrow(
        () -> new UsernameNotFoundException(String.format("User %s not found", username)));

    return new AccountUserDetails(account.get());
  }

  public UserDetails loadUserById(Integer accountId) throws UsernameNotFoundException {
    Optional<AccountModel> account = accountRepository.findById(accountId);
    account.orElseThrow(
        () ->
            new UsernameNotFoundException(String.format("User with id: %d not found", accountId)));

    return new AccountUserDetails(account.get());
  }
}
