package hu.elte.teamfinder.viewmodels;

import hu.elte.teamfinder.models.Profile;

import java.util.ArrayList;

public class ProfileViewModel {

    private long accountId;
    private String firstName;
    private String lastName;
    private Integer age;
    private Boolean isPublic;
    private String summary;
    private ArrayList<String> tags;

    public ProfileViewModel(Profile profile) {
        accountId = profile.getAccountId();
        firstName = profile.getFirstName();
        lastName = profile.getLastName();
        age = profile.getAge();
        isPublic = profile.getPublic();
        summary = profile.getSummary();
        tags = profile.getTags();
    }

    public long getAccountId() {
        return accountId;
    }
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
