package hu.elte.teamfinder.services;

import hu.elte.teamfinder.models.TeamModel;
import hu.elte.teamfinder.repos.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamModelService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamModelService(TeamRepository repo){this.teamRepository = repo;}

    public TeamModel createTeam(TeamModel team){ return teamRepository.save(team);}

    public List<TeamModel> findAllTeams() {
        return teamRepository.findAll();
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    public TeamModel getTeamById(Long id) {
        return teamRepository.getById(id);
    }
}
