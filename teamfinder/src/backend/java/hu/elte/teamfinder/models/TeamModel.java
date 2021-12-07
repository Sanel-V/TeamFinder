package hu.elte.teamfinder.models;

import javax.persistence.*;

import java.util.ArrayList;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class TeamModel {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = AUTO)
    private Long teamId;

    @Column(nullable = false, unique = true)
    private String teamName;

    private Integer teamCapacity;
    private Integer teamSize;

    @Convert(converter = LongListConverter.class)
    private ArrayList<Long> memberIds;

    private String description;

    public TeamModel(String teamName){
        this.teamName = teamName;
        this.teamCapacity = 0;
        this.teamSize = 0;
        this.memberIds = new ArrayList<>(this.teamCapacity);
        this.description = null;
    }

    public TeamModel(String teamName, Integer teamCapacity){
        this.teamName = teamName;
        this.teamCapacity = teamCapacity;
        this.teamSize = 0;
        this.memberIds = new ArrayList<>(this.teamCapacity);
        this.description = null;
    }

    public TeamModel(String teamName, Integer teamCapacity, String description){
        this.teamName = teamName;
        this.teamCapacity = teamCapacity;
        this.teamSize = 0;
        this.memberIds = new ArrayList<>(this.teamCapacity);
        this.description = description;
    }

    public Long getTeamId(){
        return this.teamId;
    }

    public String getTeamName(){
        return this.teamName;
    }

    public void setTeamName(String newName){
        this.teamName = newName;
    }

    public Integer getTeamCapacity(){
        return this.teamCapacity;
    }

    public void setTeamCapacity(Integer capacity) throws IllegalArgumentException{
        if(capacity >= 0 && capacity > teamSize){
            this.teamCapacity = capacity;
        }else {
            throw new IllegalArgumentException();
        }
    }

    public Integer getTeamSize(){
        return this.teamSize;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void addMemberId(Long accountId) throws IllegalArgumentException{
        if(this.memberIds.contains(accountId)){
            throw new IllegalArgumentException();
        }else{
            this.memberIds.add(accountId);
            this.teamSize += 1;
        }
    }

    public ArrayList<Long> getMemberIds(){
        return this.memberIds;
    }

    public void deleteMemberId(Long accountId) throws IllegalArgumentException{
        if(this.memberIds.contains(accountId)){
            throw new IllegalArgumentException();
        }else{
            this.memberIds.remove(accountId);
            this.teamSize -= 1;
        }
    }

    public void clearTeam() throws IllegalArgumentException{
        this.memberIds.clear();
        this.teamSize = 0;
    }
}
