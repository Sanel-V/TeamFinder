package hu.elte.teamfinder.repos;

import hu.elte.teamfinder.models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Integer>
{

    Optional<AccountModel> findAccountModelByEmail(String email);
    //selectAccountByEmail(String username);
    /*
    Optional<AccountModel> selectAccountByEmail(String email);

    Optional<AccountModel> getAccountById(Integer accountId);

    List<AccountModel> getAllAccounts();

    AccountModel addAccount(AccountModel account);

    void deleteAccount(); //Todo: delete profile as well*/
}