package com.epsychiatry.model.management;

import com.epsychiatry.model.base.PersistentObject;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "module")
public class Module extends PersistentObject {
    @Column(name = "module_name", unique = true, nullable = false, insertable = true, updatable = true, length = 55)
    @NotNull
    @NotEmpty
    private String moduleName;
    @Column(name = "description", unique = false, nullable = false, insertable = true, updatable = true)
    @NotNull
    @NotEmpty
    private String description;
    @Column(name = "role_prefix", unique = true, nullable = false, insertable = true, updatable = true)
    @NotNull
    @NotEmpty
    private String rolePrefix;

    @OneToMany(
            mappedBy = "module",
            fetch = FetchType.LAZY,
            cascade =  {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}
    )
    private Set<Role> roles;

    public Module() {
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRolePrefix() {
        return rolePrefix;
    }

    public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }

//    @Override
//    public String toString() {
//        return "Module{" +
//                "moduleName='" + moduleName + '\'' +
//                ", description='" + description + '\'' +
//                ", rolePrefix='" + rolePrefix + '\'' +
//                ", roles=" + roles +
//                '}';
//    }
}
