package hu.elte.teamfinder.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Profile implements Serializable {

    // TODO: connect Profile to Account
    /** ID of the account from Account */
    @Id
    @Column(nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = AUTO)
    private Integer accountId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private Integer age;
    /** Shows whether the profile is able to seen by others */
    private Boolean isPublic;
    /** Summary of the profile */
    private String summary;
    /** Shows the skills of the person */
    @Convert(converter = StringListConverter.class)
    private ArrayList<String> tags;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Account account;

    /**
     * The general contructor of the Profile
     *
     * @param accountId Id of the account form Account
     * @param firstname
     * @param lastname
     * @param age
     */
    public Profile(Integer accountId, String firstname, String lastname, Integer age) {
        this.accountId = accountId;
        this.firstName = firstname;
        this.lastName = lastname;
        this.age = age;
        this.isPublic = true;
        this.summary = "";
        this.tags = new ArrayList<String>();
    }

    public Profile() {}

    public Integer getAccountId() {
        return this.accountId;
    }

    public String getFirstname() {
        return this.firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getLastname() {
        return this.lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getIsPublic() {
        return this.isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArrayList<String> getAllTags() {
        return this.tags;
    }

    public void clearTags() {
        this.tags.clear();
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void deleteTag(String tag) {
        if (!this.tags.remove(tag)) {
            throw new IllegalArgumentException("Tag doesn't exist!");
        }
    }

    public Account getAccount() {
        return account;
    }
}
