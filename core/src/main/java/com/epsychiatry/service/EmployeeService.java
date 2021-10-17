package com.epsychiatry.service;

import com.epsychiatry.model.management.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllUsers();
    Employee saveUser(Employee e);
    Employee getEmployeeByUsername(String username);
    Employee getEmployeeByEmpId(String id);
    Employee getEmployeeByNic(String nic);
    Optional<Employee> getEmployeeByPrimaryId(Long id);
    void activeEmployee(Long id);
    void deactiveEmployee(Long id);
    void deleteEmployee(Long id);
    void saveAuth(Employee employee);
    void saveUserAuth(Employee employee);
    List<Employee> getAllEmployeeNotEqIdAndUsername(Long id, String username);
}
