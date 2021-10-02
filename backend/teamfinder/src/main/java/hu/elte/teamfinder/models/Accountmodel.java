package hu.elte.teamfinder.models;


public class Accountmodel{
    private final Integer accountId;
    private final String email;

    public Accountmodel(Integer accountId, String email){
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