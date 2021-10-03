package hu.elte.teamfinder.repos;

import hu.elte.teamfinder.models.AccountModel;

import java.util.Optional;

public interface AccountRepository //TODO: extend JPA repository
{
    //TODO: Figure if we return AccountModel or AccountUserDetails
    Optional<AccountModel> selectAccountUserDetailsByEmail(String email);
}