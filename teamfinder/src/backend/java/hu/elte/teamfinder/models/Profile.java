package hu.elte.teamfinder.models;

import hu.elte.teamfinder.utils.StringListConverter;
import org.junit.jupiter.api.Test;

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
    private Long accountId;

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    private Integer age;
    /** Shows whether the profile is able to seen by others */
    private Boolean isPublic;
    /** Summary of the profile */
    private String summary;
    /** Shows the skills of the person */
    @Convert(converter = StringListConverter.class)
    @Column(name = "tags")
    private ArrayList<String> tags;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Account account;

    /**
     * The general contructor of the Profile
     *
     * @param account account the profile is connected to
     * @param firstname
     * @param lastname
     * @param age
     */
    public Profile(Account account, String firstname, String lastname, Integer age) {
        this.account = account;
        this.accountId = account.getAccountId();
        this.firstName = firstname;
        this.lastName = lastname;
        this.age = age;
        this.isPublic = true;
        this.summary = "";
        this.tags = new ArrayList<String>();
    }

    public Profile(Account account) {
        this(account, null, null, null);
    }

    public Profile() {}

    public Long getAccountId() {
        return this.accountId;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
