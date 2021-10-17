package com.epsychiatry;

import com.epsychiatry.model.enums.Os;
import com.epsychiatry.model.management.Employee;
import com.epsychiatry.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class WebAppTest {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EmployeeService employeeService;

    @Test
    void contextLoads() {
        System.out.println(encoder.encode("pass123"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.name").compareTo(Os.LINUX.getDisplayName()));
    }

    @Test
    void queryCheck() {
        List<Employee> employees = employeeService.getAllEmployeeNotEqIdAndUsername(new Long(11), "epsuser");
        System.out.println(employees.size());
    }
}
