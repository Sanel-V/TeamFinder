package hu.elte.teamfinder.models;

import hu.elte.teamfinder.security.AccountRole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDetails extends User {

    private final Long accountId;

    public AccountDetails(Account account) {
        super(
                account.getEmail(),
                account.getPassword(),
                account.isEnabled(),
                account.isAccountNonExpired(),
                account.isCredentialsNonExpired(),
                account.isAccountNonLocked(),
                accountRolesToGrantedAuthoritySet(account));
        this.accountId = account.getAccountId();
    }

    public static Set<SimpleGrantedAuthority> accountRolesToGrantedAuthoritySet(Account account) {
        Set<SimpleGrantedAuthority> authorities =
                account.getRoles().stream()
                        .map(AccountRole::getGrantedAuthorities)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toSet());
        return authorities;
    }

    public static Set<SimpleGrantedAuthority> accountRolesToGrantedAuthoritySet(
            Set<AccountRole> accountRoles) {
        Set<SimpleGrantedAuthority> authorities =
                accountRoles.stream()
                        .map(AccountRole::getGrantedAuthorities)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toSet());
        return authorities;
    }
    /*
       //Probably not necessary
       public static Set<SimpleGrantedAuthority> accountRolesToGrantedAuthoritySet(String[] accountRoles)
       {
           Set<SimpleGrantedAuthority> authorities =
                   Arrays.stream(accountRoles)
                           .map(str -> AccountRole.valueOf(str))
                           .map(AccountRole::getGrantedAuthorities)
                           .flatMap(Collection::stream)
                           .collect(Collectors.toSet());
           return authorities;
       }
    */
    public Long getAccountId() {
        return accountId;
    }
}
