package hu.elte.teamfinder.controllers;

import java.util.Arrays;

import hu.elte.teamfinder.models.AccountModel;
import hu.elte.teamfinder.services.AccountDetailsService;

import hu.elte.teamfinder.services.AccountModelService;
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
    //TODO: ? probably exchange AccountDetailsService for a different service and AccountDetails for AccountModel
    private final AccountDetailsService accountService;
    private final AccountModelService accountModelService;

    public AccountController(AccountDetailsService accountService, AccountModelService accountModelService){
        this.accountService = accountService;
        this.accountModelService = accountModelService;
    }

    /**
     * Examples for testing accounts
     */
    //TODO: add @PreAuthorize for permission based authentication for each Mapping
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
    public ResponseEntity<List<AccountModel>> getAllAccounts(){
        List<AccountModel> accounts = accountModelService.getAllAcounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AccountModel> getAccountById(@PathVariable("id") Integer id){
        //TODO: Implement function
        AccountModel account = accountModelService.getAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AccountModel> addAccount(@RequestBody AccountModel account){
        AccountModel createdAccount = accountModelService.addAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<AccountModel> updateAccount(@RequestBody AccountModel account){
        //TODO: Implement function
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccountById(@PathVariable("id") Integer id){
        accountModelService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getUserDetails/{username}")
    public ResponseEntity<UserDetails>   loadUserByUsername(@PathVariable("username") String username){
        return new ResponseEntity<>(accountService.loadUserByUsername(username), HttpStatus.OK);
    }
}