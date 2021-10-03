package hu.elte.teamfinder.models;

import java.io.Serializable;

public class AccountModel implements Serializable{
    private final Integer accountId;
    private final String email;

    public AccountModel(Integer accountId, String email){
        this.accountId = accountId;
        this.email = email;
    }

    public Integer getAccountId(){
        return accountId;
    }

    public String getEmail(){
        return email;
    }
}