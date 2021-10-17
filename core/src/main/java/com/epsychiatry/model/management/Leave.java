package com.epsychiatry.model.management;

import com.epsychiatry.model.base.PersistentObject;
import com.epsychiatry.model.enums.LeaveStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "leaving")
public class Leave  extends PersistentObject {

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinColumn(name = "emp_id")
    private Employee emp;
    @NotNull
    @Column(name = "leave_from")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime leaveFrom;
    @NotNull
    @Column(name = "leave_to")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime leaveTo;
    @Column(name = "comment")
    private String comment;
    @Column(name = "approved_comment")
    private String approvedComment;

    @Column(name = "ask_closed", columnDefinition = "TINYINT(1) default 0")
    private Boolean askClosed;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private LeaveStatus status;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinColumn(name = "approved_by_id")
    private Employee approvedBy;

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }


    public LocalDateTime getLeaveFrom() {
        return leaveFrom;
    }

    public void setLeaveFrom(LocalDateTime leaveFrom) {
        this.leaveFrom = leaveFrom;
    }

    public LocalDateTime getLeaveTo() {
        return leaveTo;
    }

    public void setLeaveTo(LocalDateTime leaveTo) {
        this.leaveTo = leaveTo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public Employee getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Employee approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovedComment() {
        return approvedComment;
    }

    public void setApprovedComment(String approvedComment) {
        this.approvedComment = approvedComment;
    }

    public Boolean getAskClosed() {
        return askClosed;
    }

    public void setAskClosed(Boolean askClosed) {
        this.askClosed = askClosed;
    }
}
