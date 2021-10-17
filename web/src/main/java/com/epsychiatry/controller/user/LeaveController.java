package com.epsychiatry.controller.user;

import com.epsychiatry.controller.DashboardController;
import com.epsychiatry.model.enums.LeaveStatus;
import com.epsychiatry.model.management.Employee;
import com.epsychiatry.model.management.Leave;
import com.epsychiatry.security.LoggedUser;
import com.epsychiatry.service.LeaveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/module/")
public class LeaveController {

    private static final Logger logger = LogManager.getLogger(LeaveController.class);

    @Value("${max.leave.data.user.show}")
    private Integer maxShowLeaves;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private LoggedUser loggedUser;

    @GetMapping("")
    public String getModulePage(Model model) {
        logger.info("Redirect to {} user id {}", "/admin/module/", loggedUser.getUser().getId());

        return "admin/user/module";
    }

    @GetMapping("leave")
    public String getLeavePage(@ModelAttribute("leave") Leave leave, Model model) {
        logger.info("Redirect to {} user id {}", "/admin/module/leave", loggedUser.getUser().getId());
        // need to be optimized
        Employee loggedEmp = loggedUser.getUser();
        List<Leave> userLeave = leaveService.getAllLoggedUserLeavesById(loggedEmp.getId());

        model.addAttribute("leaves", userLeave);
        return  "admin/user/leave-user";
    }
    @PostMapping("leave")
    public String saveLeave(RedirectAttributes redirAttrs,@Valid @ModelAttribute("leave") Leave leave, BindingResult result, Model model) {
        logger.info("Trying to save new Leave from user id {}", loggedUser.getUser().getId());

        if(result.hasErrors()) {
            model.addAttribute("leaves", leaveService.getAllLoggedUserLeavesById(loggedUser.getUser().getId()));
            logger.info("Not Valid Leave Trying to Save by user id {}", loggedUser.getUser().getId());
            return  "admin/user/leave-user";
        }

        if(leave.getLeaveFrom().compareTo(leave.getLeaveTo()) >= 0) {
            model.addAttribute("leaves", leaveService.getAllLoggedUserLeavesById(loggedUser.getUser().getId()));
            model.addAttribute("date_from_to_err", "From Date Time cannot be larger that To Date Time");
            logger.error("Not Valid Leave Trying to Save by user id {}", loggedUser.getUser().getId());
            return  "admin/user/leave-user";
        }

        leave.setEmp(loggedUser.getUser());
        leave.setStatus(LeaveStatus.PENDING);
        leave.setAskClosed(false);
        Leave saveLeave = leaveService.saveLeave(leave);
        logger.info("save leave Id {} from user id {}", saveLeave.getId(), loggedUser.getUser().getId());

        redirAttrs.addFlashAttribute("redirectParam", "Leave is successfully Recorded");
        return "redirect:/admin/module/leave";
    }

    @GetMapping("leave/edit/{id}")
    public String getLeaveEditPage(@PathVariable("id") Long id, Model model){

        Long loggedUserId = loggedUser.getUser().getId();
        Optional<Leave> selectedLeave = leaveService.getLeaveEmpIdAndLeaveId(loggedUserId, id);

        if(!selectedLeave.isPresent()) {
            logger.info("Trying to edit not authorized  Leave id {} from user id {}",id, loggedUser.getUser().getId());
            return  "admin/exception-pages/forbidden";
        }

        if(selectedLeave.get().getStatus() != LeaveStatus.PENDING) {
            logger.info("Trying to edit status {} Leave id {} from user id {}",selectedLeave.get().getStatus().getDisplayName(), id, loggedUser.getUser().getId());
            return  "admin/exception-pages/forbidden";
        }

        model.addAttribute("leave", selectedLeave.get());
        logger.info("Trying to edit  Leave id {} from user id {}",id, loggedUser.getUser().getId());
        return "admin/user/leave-user-edit";
    }

    @PostMapping("leave/edit/{id}")
    public String updateLeave(RedirectAttributes redirAttrs,
                              @PathVariable("id") Long id,
                              @Valid @ModelAttribute("leave") Leave leave,
                              BindingResult result, Model model) {
        logger.info("Trying to save edit  Leave id {} from user id {}",id, loggedUser.getUser().getId());

        if(result.hasErrors()) {
            logger.info("Trying to save invalid edit  Leave id {} from user id {}",id, loggedUser.getUser().getId());
            return "admin/user/leave-user-edit";
        }

        if(leave.getLeaveFrom().compareTo(leave.getLeaveTo()) >= 0) {
            model.addAttribute("date_from_to_err", "From Date Time cannot be larger that To Date Time");
            logger.info("Trying to save invalid edit  Leave id {} from user id {}",id, loggedUser.getUser().getId());
            return "admin/user/leave-user-edit";
        }

        leave.setEmp(loggedUser.getUser());
        leave.setStatus(LeaveStatus.PENDING);
        leave.setAskClosed(false);
        leaveService.saveLeave(leave);
        logger.info("saved edit  Leave id {} from user id {}",id, loggedUser.getUser().getId());
        redirAttrs.addFlashAttribute("redirectParam", "Leave is successfully Updated");
        return "redirect:/admin/module/leave";

    }

    @GetMapping("leave/view/{id}")
    public String viewLeave(@PathVariable("id") Long id, Model model) {
        Long loggedUserId = loggedUser.getUser().getId();
        Optional<Leave> selectedLeave = leaveService.getLeaveEmpIdAndLeaveId(loggedUserId, id);

        if(!selectedLeave.isPresent()) {
            logger.info("Trying to view unauthorized Leave id {} from user id {}",id, loggedUser.getUser().getId());
            return  "admin/exception-pages/forbidden";
        }

        model.addAttribute("leave", selectedLeave.get());
        logger.info("viewed Leave id {} from user id {}",id, loggedUser.getUser().getId());
        return "admin/user/leave-user-view";
    }

    @GetMapping("leave/cancel/{id}")
    public String askedToCancel(RedirectAttributes redirAttrs, @PathVariable("id") Long id, Model model) {
        Long loggedUserId = loggedUser.getUser().getId();
        Optional<Leave> selectedCancelLeave = leaveService.getLeaveEmpIdAndLeaveId(loggedUserId, id);

        if(!selectedCancelLeave.isPresent()) {
            logger.info("Trying to cancel not authorized  Leave id {} from user id {}",id, loggedUser.getUser().getId());
            return  "admin/exception-pages/forbidden";
        }

        leaveService.askToCanceled(id);
        logger.info("Asked to cancel  Leave id {} from user id {}",id, loggedUser.getUser().getId());
        redirAttrs.addFlashAttribute("redirectParam", "HR Will cancel it soon");
        return "redirect:/admin/module/leave";

    }

    @GetMapping("leave/not-cancel/{id}")
    public String askedNotToCancel(RedirectAttributes redirAttrs, @PathVariable("id") Long id, Model model) {
        Long loggedUserId = loggedUser.getUser().getId();
        Optional<Leave> selectedCancelLeave = leaveService.getLeaveEmpIdAndLeaveId(loggedUserId, id);

        if(!selectedCancelLeave.isPresent()) {
            logger.info("Trying to  not cancel not authorized  Leave id {} from user id {}",id, loggedUser.getUser().getId());
            return  "admin/exception-pages/forbidden";
        }

        if(selectedCancelLeave.get().getStatus() == LeaveStatus.CANCELED) {
            logger.info("Trying to  not cancel not in cancel state Leave id {} from user id {}",id, loggedUser.getUser().getId());
            return  "admin/exception-pages/forbidden";
        }

        leaveService.askNotToCanceled(id);
        logger.info("Asked to not to cancel  Leave id {} from user id {}",id, loggedUser.getUser().getId());
        redirAttrs.addFlashAttribute("redirectParam", "HR Will not cancel it soon");
        return "redirect:/admin/module/leave";

    }

}
