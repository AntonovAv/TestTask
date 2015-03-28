package ru.returnonintelligence.testask.entities;

import javax.persistence.*;

/**
 * Created by Anton on 28.03.2015.
 * Entity for group
 */
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id", nullable = false)
    private Long id;
    @Column(name = "groupType", nullable = false)
    private String type;

    public Group() {
    }

    public Group(String type) {
        this.type = type;
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
        this.type = type;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
