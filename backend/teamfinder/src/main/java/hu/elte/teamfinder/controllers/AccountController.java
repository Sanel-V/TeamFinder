package hu.elte.teamfinder.controllers;

import java.util.Arrays;

import hu.elte.teamfinder.models.AccountModel;
import hu.elte.teamfinder.services.AccountDetailsService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountController {
    //TODO: making instance of Service
    private final AccountDetailsService accountService;

    public AccountController(AccountDetailsService accountService){
        this.accountService = accountService;
    }

    /**
     * Examples for testing accounts
     */
    private static final List<AccountModel> ACCOUNTS = Arrays.asList(
        new AccountModel(1, "anna", "password"),
        new AccountModel(2, "mark", "password"),
        new AccountModel(3, "zoli", "password")
    ) ;

    /**
     * Getting an account by ID
     * This method is only for testing while there is no database
     * @param accountId the ID of the account what we want to get
     * @return  the account of the given ID, throws Exception if not found
     */
    @GetMapping(path = "{accountId}")
    public AccountModel getAccount(@PathVariable("accountId") Integer accountId){
        return ACCOUNTS.stream()
                    .filter(account -> accountId.equals(account.getAccountId()))
                    .findFirst()
                    .orElseThrow( () -> new IllegalStateException());
    }


    @GetMapping("/all")
    public ResponseEntity<List<AccountDetailsService>> getAllAccounts(){
        //TODO: Implement function
        throw new UnsupportedOperationException();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AccountDetailsService> getAccountById(@PathVariable("id") Integer id){
        //TODO: Implement function
        throw new UnsupportedOperationException();
    }

    @PostMapping("/add")
    public ResponseEntity<AccountDetailsService> addAccount(@RequestBody AccountDetailsService account){
        //TODO: Implement function
        throw new UnsupportedOperationException();
    }

    @PutMapping("/update")
    public ResponseEntity<AccountDetailsService> updateAccount(@RequestBody AccountDetailsService account){
        //TODO: Implement function
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccountById(@PathVariable("id") Integer id){
        //TODO: Implement function
        throw new UnsupportedOperationException();
    }

    @GetMapping("/getUserDetails/{username}")
    public ResponseEntity<UserDetails>   loadUserByUsername(@PathVariable("username") String username){
        return new ResponseEntity<>(accountService.loadUserByUsername(username), HttpStatus.OK);
    }
}
