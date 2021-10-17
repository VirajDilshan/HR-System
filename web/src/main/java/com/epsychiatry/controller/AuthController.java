package com.epsychiatry.controller;

import com.epsychiatry.model.management.Employee;
import com.epsychiatry.security.LoggedUser;
import com.epsychiatry.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private LoggedUser loggedUser;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("user/edit/{id}")
    public String getUserAuthPage(@PathVariable("id") Long id, Model model) {
        String loggedUsername = loggedUser.getUser().getUsername();
        String username = null;

        if (employeeService.getEmployeeByPrimaryId(id).isPresent()) {
            username = employeeService.getEmployeeByPrimaryId(id).get().getUsername();
        }
        if (loggedUsername != username) {
            return  "admin/exception-pages/forbidden";
        }

        Employee employee = new Employee();
        employee.setId(loggedUser.getUser().getId());
        model.addAttribute("emp", employee);
        return "admin/employee-management/auth-user";
    }

    @PostMapping("user/edit/{id}")
    public String saveUserAuth(@PathVariable("id") Long id, @ModelAttribute("emp") Employee employee, Model model){
        if(employeeService.getAllEmployeeNotEqIdAndUsername(employee.getId(), employee.getUsername()).size() > 0) {
            model.addAttribute("username_exists", "username has been already used, please user different one");
            return "admin/employee-management/auth-user";
        }
        employeeService.saveUserAuth(employee);
        return "redirect:/login?logout=true";
    }
}
