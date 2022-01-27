package com.demo.featureflag.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean global = false;
    @ManyToMany
    private List<User> users = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature = (Feature) o;
        return global == feature.global && id.equals(feature.id) && title.equals(feature.title) && Objects.equals(users, feature.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, global, users);
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", global=" + global +
                ", users=" + users +
                '}';
    }
}
