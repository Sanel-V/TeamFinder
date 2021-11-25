package hu.elte.teamfinder.controllers;

import hu.elte.teamfinder.models.TeamModel;
import hu.elte.teamfinder.services.TeamModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamModelService teamService;

    public TeamController(TeamModelService service){this.teamService = service;}

    @PostMapping("/create")
    public ResponseEntity<TeamModel> createTeam(@RequestBody TeamModel team){
        TeamModel createdTeam = teamService.createTeam(team);
        return new ResponseEntity<>(createdTeam, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeamModel>> getAllTeams(){
        List<TeamModel> teams = teamService.findAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }
}
