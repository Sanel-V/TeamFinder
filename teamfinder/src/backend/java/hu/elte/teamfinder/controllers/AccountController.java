package hu.elte.teamfinder.controllers;

import hu.elte.teamfinder.models.Account;

import hu.elte.teamfinder.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    // TODO: making instance of Service
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // TODO: add @PreAuthorize for permission based authentication for each Mapping

    /*
    /**
     * Getting an account by ID
     * This method is only for testing while there is no database
     * @param accountId the ID of the account what we want to get
     * @return  the account of the given ID, throws Exception if not found

    @GetMapping(path = "{accountId}")
    public Account getAccount(@PathVariable("accountId") Integer accountId){
        return ACCOUNTS.stream()
                    .filter(account -> accountId.equals(account.getAccountId()))
                    .findFirst()
                    .orElseThrow( () -> new IllegalStateException());
    }
    */

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Integer id) {
        Account account = accountService.getAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        Account createdAccount = accountService.addAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.OK);
    }
    /*
    @PutMapping("/update")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account){
        //TODO: Implement function
        throw new UnsupportedOperationException();
    }*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccountById(@PathVariable("id") Integer id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /*
    @GetMapping("/getUserDetails/{username}")
    public ResponseEntity<UserDetails>   loadUserByUsername(@PathVariable("username") String username){
        return new ResponseEntity<>(accountService.loadUserByUsername(username), HttpStatus.OK);
    }*/
}
