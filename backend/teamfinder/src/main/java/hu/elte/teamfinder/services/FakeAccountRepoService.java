package hu.elte.teamfinder.services;

import hu.elte.teamfinder.models.AccountModel;
import hu.elte.teamfinder.repos.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository("fake")
public class FakeAccountRepoService implements AccountRepository
{

    private final PasswordEncoder passwordEncoder;

    public FakeAccountRepoService(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<AccountModel> selectAccountUserDetailsByEmail(String email)
    {
        return getAccounts().stream().filter(acc -> email.equals(acc.getEmail())).findFirst();
    }
    private List<AccountModel> getAccounts()
    {
        List<AccountModel> accounts = Arrays.asList
                (
                        (new AccountModel( 1,"anna", passwordEncoder.encode("password"))),
                        (new AccountModel( 2,"bob", passwordEncoder.encode("password123")))
                );

        return accounts;
    }



}
