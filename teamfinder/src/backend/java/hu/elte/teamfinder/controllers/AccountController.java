package hu.elte.teamfinder.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.elte.teamfinder.exceptions.UserAlreadyExists;
import hu.elte.teamfinder.models.Account;

import hu.elte.teamfinder.models.AccountDetails;
import hu.elte.teamfinder.services.AccountService;
import hu.elte.teamfinder.services.ProfileService;
import hu.elte.teamfinder.utils.JwtUtil;
import hu.elte.teamfinder.viewmodels.AccountViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final ProfileService profileService;

    @Autowired
    public AccountController(AccountService accountService, ProfileService profileService) {
        this.accountService = accountService;
        this.profileService = profileService;
    }

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
    public ResponseEntity<?> getAllAccounts(Authentication authentication) {

        if (!isAdmin(authentication)) {
            return new ResponseEntity<String>("Access denied", FORBIDDEN);
        }

        List<AccountViewModel> accountViewModels = new ArrayList<>();

        List<Account> accounts = accountService.getAllAccounts();
        accounts.forEach(account -> accountViewModels.add(new AccountViewModel(account)));

        return new ResponseEntity<List<AccountViewModel>>(accountViewModels, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAccountById(
            @PathVariable("id") Long id, Authentication authentication) {
        if (!isMeOrAdmin(id, authentication)) {
            return new ResponseEntity<String>("Access denied", FORBIDDEN);
        }
        Account account = accountService.getAccountById(id);
        return new ResponseEntity<AccountViewModel>(new AccountViewModel(account), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAccount(@RequestBody Account account) {
        Account createdAccount;
        try {
            createdAccount = accountService.addAccount(account);
        } catch (UserAlreadyExists e1) {
            return new ResponseEntity<String>(e1.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e2) {
            return new ResponseEntity<String>(e2.getMessage(), HttpStatus.BAD_REQUEST);
        }
        profileService.createProfile(account.getProfile());
        return new ResponseEntity<AccountViewModel>(
                new AccountViewModel(createdAccount), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccount(
            @PathVariable("id") Long id,
            @RequestBody Account account,
            Authentication authentication) {

        if (!isMeOrAdmin(id, authentication)) {
            return new ResponseEntity<String>("Access denied", FORBIDDEN);
        }
        Account foundAccount;
        try {
            foundAccount = accountService.getAccountById(id);
        } catch (UsernameNotFoundException e1) {
            return new ResponseEntity<String>(e1.getMessage(), HttpStatus.BAD_REQUEST);
        }
        if (account.getAccountId() == null
                || foundAccount.getAccountId() == account.getAccountId()) {
            try {
                foundAccount = accountService.updateAccount(id, account);
            } catch (UserAlreadyExists e2) {
                return new ResponseEntity<String>(e2.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<AccountViewModel>(
                new AccountViewModel(foundAccount), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccountById(
            @PathVariable("id") Long id, Authentication authentication) {
        if (!isMeOrAdmin(id, authentication)) {
            return new ResponseEntity<String>("Access denied", FORBIDDEN);
        }
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // TODO: maybe make member
        JwtUtil jwtUtil = new JwtUtil(accountService);

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(JwtUtil.HEADER_PREFIX)) {
            try {

                String refresh_token_encoded =
                        authorizationHeader.substring(JwtUtil.HEADER_PREFIX.length());
                DecodedJWT refresh_token = jwtUtil.extractJwtTokenFromHeader(authorizationHeader);
                String username = refresh_token.getSubject();
                AccountDetails account = accountService.loadUserByUsername(username);
                String access_token = jwtUtil.generateAccessToken(request, account);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token_encoded);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                // response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
    }

    private boolean isMeOrAdmin(Long id, Authentication authentication) {
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        // Only allow if user is updating their own account data or if user is admin
        if (accountDetails.getAccountId() == id
                || accountDetails
                        .getAuthorities()
                        .contains((new SimpleGrantedAuthority("ROLE_ADMIN")))) {
            return true;
        }
        return false;
    }

    private boolean isAdmin(Authentication authentication) {
        // Only allow fetching all accounts if user is admin
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
        if (accountDetails.getAuthorities().contains((new SimpleGrantedAuthority("ROLE_ADMIN")))) {
            return true;
        }
        return false;
    }
}
