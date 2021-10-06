package hu.elte.teamfinder.services;

import hu.elte.teamfinder.models.AccountModel;
import hu.elte.teamfinder.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountModelService {

    private final AccountRepository accountRepository;

    //Switch implementation of repo by switching qualifier value
    @Autowired
    public AccountModelService(@Qualifier("fake") AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    public List<AccountModel> getAllAcounts(){
        return accountRepository.getAllAccounts();
    }

    public AccountModel getAccountById(Integer id) {
        return accountRepository.getAccountById(id).orElseThrow();
    }
}
