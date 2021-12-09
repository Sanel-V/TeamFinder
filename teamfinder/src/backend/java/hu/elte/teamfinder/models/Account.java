package hu.elte.teamfinder.models;

import hu.elte.teamfinder.security.AccountRole;
import hu.elte.teamfinder.utils.StringAccountRoleSetConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Account implements Serializable {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = AUTO)
    private Long accountId;

    @Column(unique = true)
    private String email = "";

    private String password = "";

    @OneToOne(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false)
    private Profile profile;

    @Convert(converter = StringAccountRoleSetConverter.class)
    @Column
    private Set<AccountRole> roles = new HashSet<>(Arrays.asList(AccountRole.NEW_USER));
    // TODO: add Access modifiers field and update constructor
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public Account() {}

    public Account(
            String email,
            String password,
            Set<AccountRole> roles,
            Profile profile,
            boolean isAccountNonExpired,
            boolean isAccountNonLocked,
            boolean isCredentialsNonExpired,
            boolean isEnabled) {
        // this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.profile = profile;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public Account( // Integer accountId,
            String email, String password) {
        this(
                email,
                password,
                new HashSet<AccountRole>(Arrays.asList(AccountRole.NEW_USER)),
                null,
                true,
                true,
                true,
                true);
        this.profile = new Profile(this);
    }

    public Account(String email, String password, Profile profile) {
        this(
                email,
                password,
                new HashSet<AccountRole>(Arrays.asList(AccountRole.NEW_USER)),
                profile,
                true,
                true,
                true,
                true);
    }

    public Account( // Integer accountId,
            String email, String password, HashSet<AccountRole> roles) {
        this(email, password, roles, null, true, true, true, true);
    }

    public Account(String email, String password, HashSet<AccountRole> roles, Profile profile) {
        this(email, password, roles, profile, true, true, true, true);
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AccountRole> getRoles() {
        return roles;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
