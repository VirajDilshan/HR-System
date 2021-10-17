package com.epsychiatry.service.impl;

import com.epsychiatry.model.management.Leave;
import com.epsychiatry.repository.LeaveRepository;
import com.epsychiatry.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAllByOrderByLeaveFromDesc();
    }

    @Override
    public List<Leave> getAllLoggedUserLeavesById(Long id) {
        return leaveRepository.findAllLeavesForLoggedUser(id);
    }

    @Override
    public Leave saveLeave(Leave leave) {
        return leaveRepository.save(leave);
    }

    @Override
    public Optional<Leave> getLeaveEmpIdAndLeaveId(Long empId, Long leaveId) {
        return leaveRepository.findLeaveLoggedUserIdAndLeaveID(empId, leaveId);
    }

    @Override
    public List<Leave> getLatestLeaves(Integer start, Integer end) {
        return  leaveRepository.
                findAll(PageRequest.of(start, end, Sort.by(Sort.Direction.DESC, "leaveFrom"))).toList();
    }

    @Override
    public Optional<Leave> getLeaveById(Long id) {
        return leaveRepository.findById(id);
    }

    @Override
    public void askToCanceled(Long id) {
        leaveRepository.askedToClosed(id);
    }

    @Override
    public void askNotToCanceled(Long id) {
        leaveRepository.askedNotToClosed(id);
    }

    @Override
    public void updateStatusToCanceled(Long id, Long loggedId) {
        leaveRepository.updateStatusToCanceled(id, loggedId);
    }

    @Override
    public Integer getLastWeekLeaveCount() {
        return leaveRepository.getLastWeekLeaveCount();
    }
}
