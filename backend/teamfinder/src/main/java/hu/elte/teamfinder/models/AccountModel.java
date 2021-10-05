package hu.elte.teamfinder.models;

import hu.elte.teamfinder.security.AccountRole;

import java.io.Serializable;

public class AccountModel implements Serializable{
    //TODO: Make accountId auto-generated
    private final Integer accountId;
    private final String email;
    private final String password;
    private final AccountRole role; //TODO: make this a Set, Account could have multiple roles
    //TODO: add Access modifiers field and update constructor
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    public AccountModel(Integer accountId,
                        String email,
                        String password,
                        AccountRole role,
                        boolean isAccountNonExpired,
                        boolean isAccountNonLocked,
                        boolean isCredentialsNonExpired,
                        boolean isEnabled)
    {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }
    public AccountModel(Integer accountId,
                        String email,
                        String password)
    {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.role = AccountRole.STANDARD;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
    }

    public Integer getAccountId(){
        return accountId;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public AccountRole getRole() {return role;}

    public boolean isAccountNonExpired()
    {
        return isAccountNonExpired;
    }

    public boolean isAccountNonLocked()
    {
        return isAccountNonLocked;
    }

    public boolean isCredentialsNonExpired()
    {
        return isCredentialsNonExpired;
    }

    public boolean isEnabled()
    {
        return isEnabled;
    }
}