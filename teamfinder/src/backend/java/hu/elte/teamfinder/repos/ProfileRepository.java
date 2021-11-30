package hu.elte.teamfinder.repos;

import hu.elte.teamfinder.models.ProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileModel, Integer> {

    // Optional<ProfileModel> getAccountById(Integer id);

    // List<ProfileModel> getAllProfiles();

    // Optional<ProfileModel> updateProfile(ProfileModel profile);
}
