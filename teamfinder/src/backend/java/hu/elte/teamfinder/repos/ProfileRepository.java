package hu.elte.teamfinder.repos;

import hu.elte.teamfinder.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    // Optional<Profile> getAccountById(Integer id);

    // List<Profile> getAllProfiles();

    // Optional<Profile> updateProfile(Profile profile);
}
