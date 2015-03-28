package ru.returnonintelligence.testask.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * Created by Anton on 28.03.2015.
 * Entity for users
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "userName", nullable = false)
    private String userName;
    @Column(name = "userPassword", nullable = false)
    private String userPassword;
    @Column(name = "email", nullable = false)
    private String email;
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
    @ManyToOne
  //  @JoinColumn(name = "group_id", nullable = false)
    @JoinColumn(name = "group_id", nullable = true)
    private Group group;
    @ManyToOne
   // @JoinColumn(name = "address_id", nullable = false)
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;

    public User(){
    }

    public User(String firstName, String lastName, String userName, String userPassword, String email, Date birthday, Boolean active, Timestamp createTS, Timestamp lastUpdateTs, Group group, Address address) {
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
        this.userPassword = userPassword;
        this.email = email;
        this.birthday = birthday;
        this.active = active;
        this.createTS = createTS;
        this.lastUpdateTs = lastUpdateTs;
        this.group = group;
        this.address = address;
    }

    public User(String firstName, String lastName, String userName, String userPassword, String email, Date birthday, Group group, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
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
        this.firstName = firstName;
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
        this.email = email;
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
        return lastName;
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
