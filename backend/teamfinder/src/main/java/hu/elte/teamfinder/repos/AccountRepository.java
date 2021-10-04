package hu.elte.teamfinder.repos;

import hu.elte.teamfinder.models.AccountModel;

import java.util.Optional;

public interface AccountRepository //TODO: extend JPA repository
{
    Optional<AccountModel> selectAccountUserDetailsByEmail(String email);
}