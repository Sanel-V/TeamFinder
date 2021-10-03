package hu.elte.teamfinder.models;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountController {
    
    private static final List<AccountModel> ACCOUNTS = Arrays.asList(
        new AccountModel(1, "anna"),
        new AccountModel(2, "mark"),
        new AccountModel(3, "zoli")
    ) ;

    @GetMapping(path = "{accountId}")
    public AccountModel getAccount(@PathVariable("accountId") Integer accountId){
        return ACCOUNTS.stream()
                    .filter(account -> accountId.equals(account.getAccountId()))
                    .findFirst()
                    .orElseThrow( () -> new IllegalStateException());
    }
}
