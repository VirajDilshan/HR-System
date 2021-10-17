package com.epsychiatry.service;

import com.epsychiatry.model.management.Leave;

import java.util.List;
import java.util.Optional;

public interface LeaveService {

    List<Leave> getAllLeaves();
    List<Leave> getAllLoggedUserLeavesById(Long id);
    Leave saveLeave(Leave leave);
    Optional<Leave> getLeaveEmpIdAndLeaveId(Long empId, Long leaveId);
    List<Leave> getLatestLeaves(Integer start, Integer end);
    Optional<Leave> getLeaveById(Long id);
    void askToCanceled(Long id);
    void askNotToCanceled(Long id);
    void updateStatusToCanceled(Long id, Long loggedId);
    Integer getLastWeekLeaveCount();
}
