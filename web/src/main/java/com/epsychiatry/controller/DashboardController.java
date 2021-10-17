package com.epsychiatry.controller;

import com.epsychiatry.model.enums.LeaveStatus;
import com.epsychiatry.model.management.Employee;
import com.epsychiatry.model.management.Leave;
import com.epsychiatry.security.LoggedUser;
import com.epsychiatry.service.LeaveService;
import com.epsychiatry.utils.FbInsightsJsonToObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class DashboardController {
    private static final Logger logger = LogManager.getLogger(DashboardController.class);

    @Autowired
    private LoggedUser loggedUser;

    @Autowired LeaveService leaveService;

    @GetMapping("dashboard")
    public String index(Model model) {
        logger.info("/admin/dashboard");
        Employee loggedEmp = loggedUser.getUser();
        LocalDateTime now = LocalDateTime.now();

        List<Leave> userLeave = leaveService.getAllLoggedUserLeavesById(loggedEmp.getId());
        var userFutureLeave = userLeave.stream()
                .filter(leave -> leave.getLeaveFrom().compareTo(now) > 0)
                .collect(Collectors.toList());

        model.addAttribute("future_leaves", userFutureLeave);
        model.addAttribute("logged_user", loggedEmp);
        return "admin/dashboard";
    }


}
