package ru.returnonintelligence.testask.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Anton on 28.03.2015.
 * Entity for group
 */
@Entity
@Table(name = "groups")
@NamedQuery(name = "Group.getAllGroups",
            query = "SELECT g FROM Group g")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id", nullable = false)
    private Long id;

    @NotNull(message = "Group should have a type")
    @Size(min = 3, max = 40, message = "Groups's type from 3 to 40 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Use only a-zA-Z characters")
    @Column(name = "groupType", nullable = false)
    private String type;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group", orphanRemoval = true)
    private Set<User> users;

    public Group() {
    }

    public Group(String type) {
        this.type = type.toLowerCase();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.toLowerCase();
    }

    public Set<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
