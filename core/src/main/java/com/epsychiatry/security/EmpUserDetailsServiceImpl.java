package com.epsychiatry.security;

import com.epsychiatry.model.management.Employee;
import com.epsychiatry.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmpUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeService employeeService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employee employee =  employeeService.getEmployeeByUsername(s);
        if (employee == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new EmpUserDetails(employee);
    }
}
