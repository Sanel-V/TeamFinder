package hu.elte.teamfinder.controllers;

import hu.elte.teamfinder.models.ProfileModel;
import hu.elte.teamfinder.services.ProfileModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProfileController {

    private final ProfileModelService profileService;

    public  ProfileController(ProfileModelService service){
        this.profileService = service;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileModel> getProfileById(@PathVariable("id") Integer id){
        ProfileModel profile = profileService.getProfileById(id);
        return new ResponseEntity<ProfileModel>(profile, HttpStatus.OK);
    }

    @GetMapping("/allprofiles")
    public  ResponseEntity<List<ProfileModel>> getAllProfiles(){
        ArrayList<ProfileModel> profiles = profileService.getAllProfiles();
        return new ResponseEntity<List<ProfileModel>>(profiles, HttpStatus.OK);
    }


}
