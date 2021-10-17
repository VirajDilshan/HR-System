package com.epsychiatry.service.impl;

import com.epsychiatry.model.management.Employee;
import com.epsychiatry.repository.EmployeeRepository;
import com.epsychiatry.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<Employee> getAllUsers() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveUser(Employee e) {
        return employeeRepository.save(e);
    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.findEmployeesByUsername(username);
    }

    @Override
    public Employee getEmployeeByEmpId(String id) {
        return employeeRepository.findEmployeesByEmpID(id);
    }

    @Override
    public Employee getEmployeeByNic(String nic) {
        return employeeRepository.findEmployeesByNic(nic);
    }

    @Override
    public Optional<Employee> getEmployeeByPrimaryId(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void activeEmployee(Long id) {
        employeeRepository.activateEmployee(id);
    }

    @Override
    public void deactiveEmployee(Long id) {
        employeeRepository.deactivateEmployee(id);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void saveAuth(Employee employee) {
        employeeRepository.saveAuth(employee.getId(),
                employee.getUsername(),
                encoder.encode(employee.getPassword()),
                employee.isEnabled());
    }

    @Override
    public void saveUserAuth(Employee employee) {
        employeeRepository.saveUserAuth(employee.getId(),
                employee.getUsername(),
                encoder.encode(employee.getPassword()));
    }

    @Override
    public List<Employee> getAllEmployeeNotEqIdAndUsername(Long id, String username) {
        return employeeRepository.findEmployeesByUsernameForUpdateAuth(id, username);
    }
}
