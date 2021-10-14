package hu.elte.teamfinder.services;

import hu.elte.teamfinder.models.AccountModel;
import hu.elte.teamfinder.models.ProfileModel;
import hu.elte.teamfinder.repos.ProfileRepository;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository("fakeProfile")
public class FakeProfileRepository implements ProfileRepository {

    @Override
    public Optional<ProfileModel> getAccountById(Integer id) {
        //TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<ProfileModel> getAllProfiles() {
        return null;
    }

    private List<ProfileModel> createProfiles(){

        return Arrays.asList
                (
                        new ProfileModel(1, "Anna", "Smith", 13),
                        new ProfileModel(2,"Bob", "West", 22),
                        new ProfileModel(3, "Mary", "Spring", 23)
                );
    }
}
