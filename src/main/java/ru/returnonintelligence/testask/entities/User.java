package ru.returnonintelligence.testask.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * Created by Anton on 28.03.2015.
 * Entity for users
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findByUserData",
                query = "SELECT u FROM User u WHERE u.firstName = :firstName AND " +
                        "u.lastName = :lastName AND u.email = :email AND u.birthday = :birthday"),
        @NamedQuery(name = "User.getAllUsers",
                query = "SELECT u FROM User u")
})

public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @NotNull(message = "User should have a fname")
    @Size(min = 3, max = 40, message = "User's fname from 3 to 40 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Use only a-zA-Z characters")
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @NotNull(message = "User should have a lname")
    @Size(min = 3, max = 40, message = "User's lname from 3 to 40 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Use only a-zA-Z characters")
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @NotNull(message = "User should have a userName")
    @Size(min = 3, max = 40, message = "User's userName from 3 to 40 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Use only a-zA-Z0-9 characters")
    @Column(name = "userName", nullable = false)
    private String userName;

    @NotNull(message = "User should have a password")
    @Size(min = 3, max = 40, message = "User's password from 3 to 40 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Use only a-zA-Z0-9 characters")
    @Column(name = "userPassword", nullable = false)
    private String userPassword;

    @NotNull(message = "User should have a email")
    @Size(min = 3, max = 40, message = "User's email from 3 to 40 characters")
    @Pattern(regexp = "^[\\w\\.-_\\+]+@[\\w-]+(\\.\\w{2,4})+$", message = "Incorrect type of email")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull(message = "User should have a birthday")
    @Column(name = "birthday")
   // @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "isActive", nullable = false)
    private Boolean active = true;
    @Column(name = "createTS", nullable = false)
   // @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createTS;
    @Column(name = "lastUpdateTs", nullable = false)
  //  @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastUpdateTs;

    @NotNull(message = "User should have a group")
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @NotNull(message = "User should have a address")
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;

    public User(){
    }

    public User(String firstName, String lastName, String userName, String userPassword, String email, Date birthday, Boolean active, Timestamp createTS, Timestamp lastUpdateTs, Group group, Address address) {
        this.firstName = firstName.toLowerCase();
        this.userName = userName;
        this.lastName = lastName.toLowerCase();
        this.userPassword = userPassword;
        this.email = email.toLowerCase();
        this.birthday = birthday;
        this.active = active;
        this.createTS = createTS;
        this.lastUpdateTs = lastUpdateTs;
        this.group = group;
        this.address = address;
    }

    public User(String firstName, String lastName, String userName, String userPassword, String email, Date birthday, Group group, Address address) {
        this.firstName = firstName.toLowerCase();
        this.lastName = lastName.toLowerCase();
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email.toLowerCase();
        this.birthday = birthday;
        this.group = group;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toLowerCase();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Timestamp getLastUpdateTs() {
        return lastUpdateTs;
    }

    public void setLastUpdateTs(Timestamp lastUpdateTs) {
        this.lastUpdateTs = lastUpdateTs;
    }

    public Timestamp getCreateTS() {
        return createTS;
    }

    public void setCreateTS(Timestamp createTS) {
        this.createTS = createTS;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName.toLowerCase();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", active=" + active +
                ", createTS=" + createTS +
                ", lastUpdateTs=" + lastUpdateTs +
                ", group=" + group +
                ", address=" + address +
                '}';
    }

}
