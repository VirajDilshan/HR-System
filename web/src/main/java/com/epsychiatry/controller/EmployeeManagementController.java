package com.epsychiatry.controller;

import com.epsychiatry.model.management.*;
import com.epsychiatry.model.management.Module;
import com.epsychiatry.security.LoggedUser;
import com.epsychiatry.service.*;
import com.epsychiatry.utils.FileUploadUtil;
import com.epsychiatry.utils.OS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/employee/management")
public class EmployeeManagementController {
    private static final Logger logger = LogManager.getLogger(EmployeeManagementController.class);
    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LoggedUser loggedUser;

    private String getDefaultImageName() {
        return "esy_logo.jpg";
    }


    /**
     * @des group management
     * @param model
     * @return
     */

    @GetMapping("group")
    public String getGroupManagement(Model model){
        List<UserGroup> groups = userGroupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "admin/employee-management/group-management";
    }

    @GetMapping("group/add")
    public String addNewGroup(@ModelAttribute("userGroup") UserGroup userGroup, Model model) {
        return "admin/employee-management/group-add";
    }

    @PostMapping("group/add")
    public String saveAddNewGroup(@Valid @ModelAttribute("userGroup") UserGroup userGroup, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "admin/employee-management/group-add";
        }
        // check group name already exits or not
        if(userGroupService.getUserGroupByName(userGroup.getName()) != null){
            model.addAttribute("group_exists", "group is already exists");
            return "admin/employee-management/group-add";
        }
        userGroupService.saveUserGroup(userGroup);
        return "redirect:/admin/employee/management/group";
    }

    @GetMapping("group/delete/{id}")
    public String deleteUserGroup (@PathVariable("id") Long id, Model model) {

        if(userGroupService.getUserGroupById(id).isPresent()){
            userGroupService.deleteUserGroupById(id);
            logger.info("group is deleted");
        } else {
            logger.info("group is not deleted");
        }

        return "redirect:/admin/employee/management/group";
    }

    @GetMapping("group/activate/{id}")
    public String activateGroup(@PathVariable("id") Long id, Model model) {
        if(userGroupService.getUserGroupById(id).isPresent()){
            userGroupService.activateGroup(id);
        }

        return "redirect:/admin/employee/management/group";
    }

    @GetMapping("group/deactivate/{id}")
    public String deactivateGroup(@PathVariable("id") Long id, Model model) {
        if(userGroupService.getUserGroupById(id).isPresent()){
            userGroupService.deactivateGroup(id);
        }

        return "redirect:/admin/employee/management/group";
    }

    @GetMapping("roles/assignment")
    public String assignRoles(@ModelAttribute("user_group") UserGroup userGroup, Model model){
        model.addAttribute("groups", userGroupService.getAllGroups());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/employee-management/role-assign";
    }

    @PostMapping("roles/assignment")
    public String saveassignRoles(@ModelAttribute("user_group") UserGroup userGroup, Model model){

        userGroupService.saveUserGroup(userGroup);
        return "admin/employee-management/role-assign";
    }

    @GetMapping("group/edit/{id}")
    public String getGroupEditPage(@PathVariable("id") Long id,
                                   Model model) {
        UserGroup userGroup = userGroupService.getUserGroupById(id).get();
        model.addAttribute("user_group", userGroup);
        model.addAttribute("all_roles", roleService.getAllRoles());
        String loginUserGroup = loggedUser.getUser().getUserGroup().getName();
        String ChangeGroup = userGroup.getName();
        return  getRoute(loginUserGroup, ChangeGroup, "admin/employee-management/group-edit");
    }

    @PostMapping("group/edit/{id}")
    public String editGroupEditPage(@Valid @ModelAttribute("user_group") UserGroup userGroup,
                                    @PathVariable("id") Long id,
                                    BindingResult result,
                                    Model model) {

        if(result.hasErrors()) {
            model.addAttribute("user_group", userGroup);
            model.addAttribute("all_roles", roleService.getAllRoles());
            return "admin/employee-management/group-edit";
        }

        userGroupService.saveUserGroup(userGroup);
        return "redirect:/admin/employee/management/group";
    }

    /**
     * @des roles management
     * @param model
     * @return
     */

    @GetMapping("roles")
    public String getRolesManagement(Model model){
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("modules", moduleService.getAllModules());
        return "admin/employee-management/role-management";
    }

    @GetMapping("module/add")
    public String getModulePage(@ModelAttribute("module") Module module, Model model) {
        return "admin/employee-management/module-add";
    }

    @PostMapping("module/add")
    public String saveModulePage(@Valid @ModelAttribute("module") Module module, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "admin/employee-management/module-add";
        }

        // check group name already exits or not
        if(moduleService.getModuleByRolePrefix(module.getRolePrefix()) != null){
            model.addAttribute("module_exists", "module is already exists");
            return "admin/employee-management/module-add";
        }

        moduleService.saveModule(module);
        return "redirect:/admin/employee/management/roles";
    }

    @GetMapping("role/add")
    public String getRoleAddPage(@ModelAttribute("role") Role role, Model model) {
        model.addAttribute("modules", moduleService.getAllModules());
        return "admin/employee-management/role-add";
    }

    @PostMapping("role/add")
    public String saveRole(@Valid @ModelAttribute("role") Role role ,BindingResult result,  Model model) {
        if(result.hasErrors()) {
            model.addAttribute("modules", moduleService.getAllModules());
            return "admin/employee-management/role-add";
        }
        roleService.saveRole(role);
        return "redirect:/admin/employee/management/roles";
    }

    @GetMapping("role/delete/{id}")
    public String deleteRole(@PathVariable("id") Long id, Model model) {
        roleService.deleteRoleById(id);
        return "redirect:/admin/employee/management/roles";
    }

    @GetMapping("/role/edit/{id}")
    public String getRoleEditPage(@PathVariable("id") Long id,
                                   Model model) {
        Role role = roleService.getRoleById(id).get();
        model.addAttribute("role", role);
        model.addAttribute("all_groups", userGroupService.getAllGroups());
        model.addAttribute("all_modules", moduleService.getAllModules());
        return "admin/employee-management/role-edit";
    }

    @PostMapping("/role/edit/{id}")
    public String saveEditGroupEdit(@Valid @ModelAttribute("role") Role role,
                                    @PathVariable("id") Long id,
                                    BindingResult result,
                                    Model model) {

        if(result.hasErrors()) {
            Role role1 = roleService.getRoleById(id).get();
            model.addAttribute("role", role1);
            model.addAttribute("all_groups", userGroupService.getAllGroups());
            model.addAttribute("all_modules", moduleService.getAllModules());
            return "admin/employee-management/role-edit";
        }

        roleService.saveRole(role);
        return "redirect:/admin/employee/management/roles";
    }

    /**
     *  employee management
     */

    @GetMapping("emp")
    public String getEmpPage(Model model) {
        model.addAttribute("emps", employeeService.getAllUsers());
        for(Employee e: employeeService.getAllUsers()) {
            System.out.println(e.getAvatarImagePath());
        }
        return "admin/employee-management/emp-management";
    }

    @GetMapping("emp/add")
    public String getUserAddPage(@ModelAttribute("emp") Employee employee, Model model) {
        model.addAttribute("groups", userGroupService.getAllGroups());
        return "admin/employee-management/emp-add";
    }

    @PostMapping("emp/add")
    public String saveNewUser(@RequestParam("avatar-img") MultipartFile multipartFile,
                              @Valid @ModelAttribute("emp") Employee employee,
                              BindingResult result,
                              Model model) throws IOException {
        System.out.println("awa");
        if(employeeService.getEmployeeByEmpId(employee.getEmpID()) != null) {
            model.addAttribute("emp_id_exists", "Emp Id is already exists, used different one");
            model.addAttribute("groups", userGroupService.getAllGroups());
            return "admin/employee-management/emp-add";
        }

        if(employeeService.getEmployeeByNic(employee.getNic()) != null) {
            model.addAttribute("nic_exists", "NIC should be unique, Someone got already this NIC");
            model.addAttribute("groups", userGroupService.getAllGroups());
            return "admin/employee-management/emp-add";
        }

        if(result.hasErrors()) {
            model.addAttribute("groups", userGroupService.getAllGroups());
            return "admin/employee-management/emp-add";
        }
        saveImageAndEmp(multipartFile, employee);
        return "redirect:/admin/employee/management/emp";
    }

    private void saveImageAndEmp(MultipartFile multipartFile, Employee employee) throws IOException {
        String uploadDir = null;
        if(multipartFile.isEmpty()){ //save default image
            String imgFileName = getDefaultImageName();
            employee.setAvatar(imgFileName);
            Employee editEmp = employeeService.saveUser(employee);
            if(OS.isLinux()){
                uploadDir = "/eps/images/avatar/" + editEmp.getId();
            }else if(OS.isWindows10()) {
                uploadDir = "C:\\eps\\images\\avatar\\" + editEmp.getId();
            }
            FileUploadUtil.defaultSaveFile(uploadDir, imgFileName);
        }else {
            // save income img
            String imgFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            employee.setAvatar(imgFileName);
            Employee editEmp = employeeService.saveUser(employee);
            if(OS.isLinux()) {
                uploadDir = "/eps/images/avatar/" + editEmp.getId();
            }else if(OS.isWindows10()) {
                uploadDir = "C:\\eps\\images\\avatar\\" + editEmp.getId();
            }
            FileUploadUtil.saveFile(uploadDir, imgFileName, multipartFile);
        }
    }

    @GetMapping("emp/edit/{id}")
    public String editUserPage(@PathVariable("id") Long id, Model model) {
        Employee emp = employeeService.getEmployeeByPrimaryId(id).get();
        model.addAttribute("emp", emp);
        model.addAttribute("groups", userGroupService.getAllGroups());

        String loginUserGroup = loggedUser.getUser().getUserGroup().getName();
        String ChangeGroup = emp.getUserGroup().getName();
        return  getRoute(loginUserGroup, ChangeGroup, "admin/employee-management/emp-edit");
    }

    private String getRoute(String loginUserGroup, String ChangeGroup, String successPath) {
        if(loginUserGroup.equals("SUPER DEV") || loginUserGroup.equals("SUPER ADMIN")) {
            return  successPath;
        } else if (loginUserGroup.equals("SYS ADMIN")) {
            String changeEmpGroup = ChangeGroup;
            if(changeEmpGroup.equals("SUPER DEV") || changeEmpGroup.equals("SUPER ADMIN")) {
                return  "admin/exception-pages/forbidden";
            } else if (changeEmpGroup.equals("SYS ADMIN")){
                return  "admin/exception-pages/forbidden";
            } else{
                return  successPath;
            }

        }else {
            System.out.println("user permission not right");
            return null;
        }
    }

    @PostMapping("emp/edit/{id}")
    public String editUser(@RequestParam("avatar-img") MultipartFile multipartFile,
                           @PathVariable("id") Long id,
                           @Valid @ModelAttribute("emp") Employee employee,
                           BindingResult result,
                           Model model) throws IOException {

        String uploadDir = null;
        Employee curEmp = employeeService.getEmployeeByPrimaryId(id).get();

        if(result.hasErrors()) {
            model.addAttribute("emp", curEmp);
            model.addAttribute("groups", userGroupService.getAllGroups());
            return  "admin/employee-management/emp-edit";
        }
        employee.setUsername(curEmp.getUsername());
        employee.setPassword(curEmp.getPassword());
        employee.setEnabled(curEmp.isEnabled());

        saveImageAndEmp(multipartFile, employee);
        return "redirect:/admin/employee/management/emp";
    }

    @GetMapping("emp/activate/{id}")
    public String activateEmp(@PathVariable("id") Long id, Model model) {
        String loginUserGroup = loggedUser.getUser().getUserGroup().getName();
        Employee emp = employeeService.getEmployeeByPrimaryId(id).get();
        String ChangeGroup = emp.getUserGroup().getName();

        if(loginUserGroup.equals("SUPER DEV") || loginUserGroup.equals("SUPER ADMIN")) {
            if(employeeService.getEmployeeByPrimaryId(id).isPresent()){
                employeeService.activeEmployee(id);
            }
            return "redirect:/admin/employee/management/emp";
        } else if (loginUserGroup.equals("SYS ADMIN")) {
            String changeEmpGroup = ChangeGroup;
            if(changeEmpGroup.equals("SUPER DEV") || changeEmpGroup.equals("SUPER ADMIN")) {
                return  "admin/exception-pages/forbidden";
            } else if (changeEmpGroup.equals("SYS ADMIN")){
                return  "admin/exception-pages/forbidden";
            } else{
                if(employeeService.getEmployeeByPrimaryId(id).isPresent()){
                    employeeService.activeEmployee(id);
                }
                return "redirect:/admin/employee/management/emp";
            }

        }else {
            System.out.println("user permission not right");
            return null;
        }

    }

    @GetMapping("emp/deactivate/{id}")
    public String deactivateEmp(@PathVariable("id") Long id, Model model) {
        String loginUserGroup = loggedUser.getUser().getUserGroup().getName();
        Employee emp = employeeService.getEmployeeByPrimaryId(id).get();
        String ChangeGroup = emp.getUserGroup().getName();

        if(loginUserGroup.equals("SUPER DEV") || loginUserGroup.equals("SUPER ADMIN")) {
            if(employeeService.getEmployeeByPrimaryId(id).isPresent()){
                employeeService.deactiveEmployee(id);
            }
            return "redirect:/admin/employee/management/emp";
        } else if (loginUserGroup.equals("SYS ADMIN")) {
            String changeEmpGroup = ChangeGroup;
            if(changeEmpGroup.equals("SUPER DEV") || changeEmpGroup.equals("SUPER ADMIN")) {
                return  "admin/exception-pages/forbidden";
            } else if (changeEmpGroup.equals("SYS ADMIN")){
                return  "admin/exception-pages/forbidden";
            } else{
                if(employeeService.getEmployeeByPrimaryId(id).isPresent()){
                    employeeService.deactiveEmployee(id);
                }
                return "redirect:/admin/employee/management/emp";
            }

        }else {
            System.out.println("user permission not right");
            return null;
        }

    }

    @GetMapping("emp/delete/{id}")
    public String deleteEmp (@PathVariable("id") Long id, Model model) {
        String loginUserGroup = loggedUser.getUser().getUserGroup().getName();
        Employee emp = employeeService.getEmployeeByPrimaryId(id).get();
        String ChangeGroup = emp.getUserGroup().getName();

        if(loginUserGroup.equals("SUPER DEV") || loginUserGroup.equals("SUPER ADMIN")) {
            if(employeeService.getEmployeeByPrimaryId(id).isPresent()){
                employeeService.deleteEmployee(id);
                logger.info("emp is deleted");
            } else {
                logger.info("emp is not deleted");
            }
            return "redirect:/admin/employee/management/emp";
        } else if (loginUserGroup.equals("SYS ADMIN")) {
            String changeEmpGroup = ChangeGroup;
            if(changeEmpGroup.equals("SUPER DEV") || changeEmpGroup.equals("SUPER ADMIN")) {
                return  "admin/exception-pages/forbidden";
            } else if (changeEmpGroup.equals("SYS ADMIN")){
                return  "admin/exception-pages/forbidden";
            } else{
                if(employeeService.getEmployeeByPrimaryId(id).isPresent()){
                    employeeService.deleteEmployee(id);
                    logger.info("emp is deleted");
                } else {
                    logger.info("emp is not deleted");
                }
                return "redirect:/admin/employee/management/emp";
            }

        }else {
            System.out.println("user permission not right");
            return null;
        }

    }

    @GetMapping("auth/edit/{id}")
    public String getAuthPage(@PathVariable("id") Long id, Model model) {
        String loginUserGroup = loggedUser.getUser().getUserGroup().getName();
        Employee emp = employeeService.getEmployeeByPrimaryId(id).get();
        String ChangeGroup = emp.getUserGroup().getName();
        Employee employee = new Employee();
        employee.setId(id);
        employee.setEnabled(emp.isEnabled());
        model.addAttribute("emp", employee);
        model.addAttribute("emp_pri_id", id);

        return getRoute(loginUserGroup, ChangeGroup, "admin/employee-management/auth");
    }

    @PostMapping("auth/edit/{id}")
    public String saveAuth(@PathVariable("id") Long id, @ModelAttribute("emp") Employee employee, Model model){
        if(employeeService.getAllEmployeeNotEqIdAndUsername(id, employee.getUsername()).size() > 0) {
            model.addAttribute("username_exists", "username has been already used, please user different one");
            return "admin/employee-management/auth";
        }
        employee.setId(id);
        employeeService.saveAuth(employee);
        return "redirect:/admin/employee/management/emp";

    }

}
