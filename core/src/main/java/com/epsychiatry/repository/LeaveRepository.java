package com.epsychiatry.repository;

import com.epsychiatry.model.management.Leave;
import com.epsychiatry.repository.custom.LeaveCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long>, LeaveCustomRepository {

    @Query("SELECT leave FROM Leave leave ORDER BY  leave.leaveFrom")
    List<Leave> findAllByOrderByLeaveFromDesc();

    @Query("SELECT l FROM Leave l WHERE l.emp.id = ?1 ORDER BY l.leaveFrom DESC")
    List<Leave> findAllLeavesForLoggedUser(Long id);

    @Query("SELECT l FROM Leave l WHERE l.emp.id = ?1 AND l.id = ?2")
    Optional<Leave> findLeaveLoggedUserIdAndLeaveID(Long emp_id, Long leave_id);

    @Query("SELECT leave FROM Leave leave order by leave.id DESC")
    List<Leave> findAllLatestLeaves(Pageable pageable);








}
