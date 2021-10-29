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

    @Autowired
    public AccountModelService(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    public List<AccountModel> getAllAccounts(){
        return accountRepository.findAll();
    }

    public AccountModel getAccountById(Integer id) {
        return accountRepository.getById(id);
    }

    public AccountModel addAccount(AccountModel account){
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