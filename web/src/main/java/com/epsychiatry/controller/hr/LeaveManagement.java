package com.epsychiatry.controller.hr;

import com.epsychiatry.controller.user.LeaveController;
import com.epsychiatry.model.enums.LeaveStatus;
import com.epsychiatry.model.management.Leave;
import com.epsychiatry.security.LoggedUser;
import com.epsychiatry.service.LeaveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/hr")
public class LeaveManagement {
    private static final Logger logger = LogManager.getLogger(LeaveManagement.class);
    private LocalDateTime now = LocalDateTime.now();

    @Value("${max.leave.data.user.hr.show}")
    private Integer maxLeaves;

    @Autowired
    private LoggedUser loggedUser;

    @Autowired
    private LeaveService leaveService;

    @GetMapping("management/leave")
    public String getLeaveManagement(Model model) {
        logger.info("Redirect to {} user id {}", "/admin/hr/management/leave", loggedUser.getUser().getId());
        List<Leave> leaves = leaveService.getLatestLeaves(0,maxLeaves);
        var userFutureLeave = leaveService.getAllLeaves().stream()
                .filter(leave1 -> leave1.getLeaveFrom().compareTo(now) > 0)
                .collect(Collectors.toList());

        model.addAttribute("tbl_leaves", leaves);
        model.addAttribute("future_leaves", userFutureLeave);
        model.addAttribute("last_week_count", leaveService.getLastWeekLeaveCount());
        return "admin/hr/leave-management";
    }

    @GetMapping("management/leaves/all")
    public String getAllLeaves(Model model) {
        logger.info("Redirect to {} user id {}", "/admin/hr/management/leaves/all", loggedUser.getUser().getId());
        model.addAttribute("tbl_leaves", leaveService.getAllLeaves());
        return "admin/hr/leave-hr-leave-all";
    }

    @GetMapping("management/leave/review/{id}")
    public String getLeaveReview(@PathVariable("id") Long id, Model model) {
        Optional<Leave> leave = leaveService.getLeaveById(id);

        if(!leave.isPresent()) {
            logger.info("Trying to review not existing leave id {} from  user id {}", id, loggedUser.getUser().getId());
            return  "admin/exception-pages/forbidden";
        }

        if(leave.get().getStatus() != LeaveStatus.PENDING) {
            logger.info("Trying to review not pending leave id {} from  user id {}", id, loggedUser.getUser().getId());
            return  "admin/exception-pages/forbidden";
        }

        model.addAttribute("leave", leave.get());

        logger.info("Trying to review leave id {} from  user id {}", id, loggedUser.getUser().getId());
        return "admin/hr/leave-review";
    }

    @PostMapping("management/leave/review/{id}")
    public String saveReview(RedirectAttributes redirAttrs,
                             @PathVariable("id") Long id,
                             @Valid @ModelAttribute("leave") Leave leave,
                             BindingResult result,
                             Model model) {
        logger.info("Trying to save review  Leave id {} from user id {}",id, loggedUser.getUser().getId());

        if(result.hasErrors()) {
            logger.info("Trying to save invalid review  Leave id {} from user id {}",id, loggedUser.getUser().getId());
            return "admin/hr/leave-review";
        }

        leave.setApprovedBy(loggedUser.getUser());
        leave.setEmp(leaveService.getLeaveById(id).get().getEmp());
        leave.setAskClosed(leaveService.getLeaveById(id).get().getAskClosed());
        leaveService.saveLeave(leave);
        logger.info("saved review  Leave id {} from user id {}",id, loggedUser.getUser().getId());
        return "redirect:/admin/hr/management/leave";

    }

    @GetMapping("management/leave/cancel/{id}")
    public String cancelLeave(@PathVariable("id") Long id, Model model) {
        logger.info("cancel leave id {} from  user id {}", id, loggedUser.getUser().getId());
        Long loggedUserId = loggedUser.getUser().getId();
        leaveService.updateStatusToCanceled(id, loggedUserId);
        return "redirect:/admin/hr/management/leave";
    }

    @GetMapping("management/leave/view/{id}")
    public String viewLeave(@PathVariable("id") Long id, Model model) {
        logger.info("View leave id {} from  user id {}", id, loggedUser.getUser().getId());

        Optional<Leave> leave = leaveService.getLeaveById(id);
        if(!leave.isPresent()) {
            logger.info("Trying to view not existing leave id {} from  user id {}", id, loggedUser.getUser().getId());
            return  "admin/exception-pages/forbidden";
        }

        model.addAttribute("leave", leave.get());

        return "admin/hr/leave-hr-view";
    }

}
