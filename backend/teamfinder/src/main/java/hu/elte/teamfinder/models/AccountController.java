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
    
    private static final List<Accountmodel> ACCOUNTS = Arrays.asList(
        new Accountmodel(1, "anna"),
        new Accountmodel(2, "mark"),
        new Accountmodel(3, "zoli")
    ) ;

    @GetMapping(path = "{accountId}")
    public Accountmodel getAccount(@PathVariable("accountId") Integer accountId){
        return ACCOUNTS.stream()
                    .filter(account -> accountId.equals(account.getAccountId()))
                    .findFirst()
                    .orElseThrow( () -> new IllegalStateException());
    }
}
