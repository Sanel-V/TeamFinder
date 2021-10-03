package hu.elte.teamfinder.models;

import java.io.Serializable;

public class AccountModel implements Serializable{
    private final Integer accountId;
    private final String email;
    private final String password;
    //TODO: Figure out if basically implementing all methods of UserDetails is a good idea
    //I don't want to hardcode values in AccountUserDetails
    //That also means saving all of these values in the db (?)

    //TODO: Write a security config and roles/permissions
    public AccountModel(Integer accountId, String email, String password){
        this.accountId = accountId;
        this.email = email;
        this.password = password;
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
}