package hu.elte.teamfinder.controllers;

import java.util.Arrays;

import hu.elte.teamfinder.models.AccountModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountController {
    //TODO: add @PreAuthorize for permission based authentication for each Mapping
    private static final List<AccountModel> ACCOUNTS = Arrays.asList(
        new AccountModel(1, "anna", "password"),
        new AccountModel(2, "mark", "password"),
        new AccountModel(3, "zoli", "password")
    ) ;

    @GetMapping(path = "{accountId}")
    public AccountModel getAccount(@PathVariable("accountId") Integer accountId){
        return ACCOUNTS.stream()
                    .filter(account -> accountId.equals(account.getAccountId()))
                    .findFirst()
                    .orElseThrow( () -> new IllegalStateException());
    }
}
