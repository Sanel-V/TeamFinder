package hu.elte.teamfinder.repos;

import hu.elte.teamfinder.models.TeamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamModel, Long> {
}
