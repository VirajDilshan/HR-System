package com.epsychiatry.security;

import com.epsychiatry.model.management.Employee;
import com.epsychiatry.model.management.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class EmpUserDetails implements UserDetails {

    private Employee employee;

    public EmpUserDetails(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = employee.getUserGroup().getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return employee.isEnabled();
    }

    public String getFullName() {
        return employee.getFirstName() + " "+ employee.getLastName() + " ";
    }

    public String getAvatarURL() {
        return employee.getAvatarImagePath();
    }

    public String getGroup() {
        return employee.getUserGroup().getName();
    }

    public String getPosition() {
        return employee.getPosition().getDisplayName();
    }

    public String getDepartment() {
        return employee.getDepartment().getDisplayName();
    }

    public Long getPriId() {
        return employee.getId();
    }


}
