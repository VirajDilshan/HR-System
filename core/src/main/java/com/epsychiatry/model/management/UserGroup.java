package com.epsychiatry.model.management;

import com.epsychiatry.model.base.PersistentObject;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_group")
public class UserGroup extends PersistentObject {

    @Column(name = "name", unique = true, nullable = true, insertable = true, updatable = true)
    @NotEmpty
    @NotNull
    private String name;
    @Column(name = "description", unique = false, nullable = true, insertable = true, updatable = true)
    @NotEmpty
    @NotNull
    private String description;
    @Column(name = "activate")
    private Boolean activate;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "user_group_roles",
            joinColumns = @JoinColumn(name = "user_group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(
            mappedBy = "userGroup",
            cascade =  {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}
    )
    private List<Employee> employees;

    public UserGroup() {
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    //    @Override
//    public String toString() {
//        return "UserGroup{" +
//                "name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", activate=" + activate +
//                ", roles=" + roles +
//                '}';
//    }
}
