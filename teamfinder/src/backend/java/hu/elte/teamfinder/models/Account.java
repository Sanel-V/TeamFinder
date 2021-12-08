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
    // TODO: join with Profile
    // TODO: Make accountId auto-generated
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = AUTO)
    private Integer accountId;

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
            // Integer accountId,
            String email,
            String password,
            Set<AccountRole> roles,
            boolean isAccountNonExpired,
            boolean isAccountNonLocked,
            boolean isCredentialsNonExpired,
            boolean isEnabled) {
        // this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.roles = roles;
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
                true,
                true,
                true,
                true);
    }

    public Account( // Integer accountId,
            String email, String password, HashSet<AccountRole> roles) {
        this(email, password, roles, true, true, true, true);
    }

    public Integer getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
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
}
