package hu.elte.teamfinder.models;

import hu.elte.teamfinder.security.AccountRole;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class AccountModel implements Serializable{
    //TODO: join with ProfileModel
    //TODO: Make accountId auto-generated
    @Id
    @Column(nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = AUTO)
    private Long accountId;
    @Column(unique = true)
    private final String email;

    private final String password;
    private final AccountRole role; //TODO: make this a Set, Account could have multiple roles
    //TODO: add Access modifiers field and update constructor
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private ProfileModel profile;

    public AccountModel()
    {
        this.email = null;
        this.password = null;
        this.role = AccountRole.STANDARD;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
        this.profile = null;
    }


    public AccountModel(String email,
                        String password,
                        AccountRole role,
                        boolean isAccountNonExpired,
                        boolean isAccountNonLocked,
                        boolean isCredentialsNonExpired,
                        boolean isEnabled,
                        ProfileModel profile)
    {
        this.email = email;
        this.password = password;
        this.role = role;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.profile = profile;
    }
    public AccountModel(String email,
                        String password)
    {
        this.email = email;
        this.password = password;
        this.role = AccountRole.STANDARD;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
        this.profile = new ProfileModel();
    }


    public Long getAccountId(){
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

    public ProfileModel getProfile() {
        return profile;
    }
}