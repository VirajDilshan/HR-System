package com.epsychiatry.model.management;

import com.epsychiatry.model.base.PersistentObject;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role extends PersistentObject {

    @Column(name = "name", unique = true, nullable = false, insertable = true, updatable = true, length = 55)
    @NotNull
    @NotEmpty
    private String name;
    @Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true)
    @NotNull
    @NotEmpty
    private String description;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "user_group_roles",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_group_id", referencedColumnName = "id")
    )
    private Set<UserGroup> userGroups;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinColumn(name = "module_id")
    private Module module;

    public Role() {
    }

    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
//
//    @Override
//    public String toString() {
//        return "Role{" +
//                "name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", userGroups=" + userGroups +
//                ", module=" + module +
//                '}';
//    }
}
