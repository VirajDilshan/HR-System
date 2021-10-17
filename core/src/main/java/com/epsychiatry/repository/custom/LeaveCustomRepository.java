package com.epsychiatry.repository.custom;

public interface LeaveCustomRepository {
    void askedToClosed(Long id);
    void askedNotToClosed(Long id);
    void updateStatusToCanceled(Long id, Long loggedId);

    Integer getLastWeekLeaveCount();
}
