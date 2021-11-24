package hu.elte.teamfinder.repos;

import hu.elte.teamfinder.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    // Optional<Profile> getAccountById(Integer id);

    // List<Profile> getAllProfiles();

    // Optional<Profile> updateProfile(Profile profile);
}
