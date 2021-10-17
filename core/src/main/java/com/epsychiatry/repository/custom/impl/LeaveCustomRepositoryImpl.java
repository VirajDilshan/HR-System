package com.epsychiatry.repository.custom.impl;

import com.epsychiatry.model.management.Leave;
import com.epsychiatry.repository.custom.LeaveCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LeaveCustomRepositoryImpl implements LeaveCustomRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public void askedToClosed(Long id) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("UPDATE leaving SET ask_closed = 1 WHERE id = ?", id);
    }

    @Override
    public void askedNotToClosed(Long id) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("UPDATE leaving SET ask_closed = 0 WHERE id = ?", id);
    }

    @Override
    public void updateStatusToCanceled(Long id, Long loggedId) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("UPDATE leaving SET status = 3, approved_by_id = ? WHERE id = ?",loggedId, id);
    }

    @Override
    public Integer getLastWeekLeaveCount() {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        List<Leave> leaves = template.query("SELECT id FROM leaving where leave_from  BETWEEN date_sub(now(), INTERVAL 2 WEEK) AND now();", new RowMapper<Leave>() {
            @Override
            public Leave mapRow(ResultSet resultSet, int i) throws SQLException {
                Leave leave = new Leave();
                leave.setId(resultSet.getLong("id"));
                return leave;
            }
        });

        return leaves.size();
    }
}
