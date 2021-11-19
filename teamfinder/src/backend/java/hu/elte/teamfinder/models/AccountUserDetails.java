package hu.elte.teamfinder.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class AccountUserDetails implements UserDetails
{
    private final String email;
    private final Long accountId;
    private final String password;
    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    public AccountUserDetails(AccountModel account)
    {
        this.email = account.getEmail();
        this.accountId = account.getAccountId();
        this.password = account.getPassword();
        //TODO: When roles are a Set: unify all authority set into one
        this.grantedAuthorities = account.getRole().getGrantedAuthorities();
        this.isAccountNonExpired = account.isAccountNonExpired();
        this.isAccountNonLocked = account.isAccountNonLocked();
        this.isCredentialsNonExpired = account.isCredentialsNonExpired();
        this.isEnabled = account.isEnabled();
    }

    public Long getAccountId()
    {
        return accountId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


}