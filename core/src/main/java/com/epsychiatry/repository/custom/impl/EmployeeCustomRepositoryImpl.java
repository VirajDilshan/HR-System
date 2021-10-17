package com.epsychiatry.repository.custom.impl;

import com.epsychiatry.repository.custom.EmployeeCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public void activateEmployee(Long id) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("UPDATE employee SET enabled = 1 WHERE id = ?", id);
    }

    @Override
    public void deactivateEmployee(Long id) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("UPDATE employee SET enabled = 0 WHERE id = ?", id);
    }

    @Override
    public void saveAuth(Long id, String username, String password, boolean enabled) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("UPDATE employee SET username = ? , password = ?, enabled = ? WHERE id = ?",
                username, password, enabled, id);
    }

    @Override
    public void saveUserAuth(Long id, String username, String password) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("UPDATE employee SET username = ? , password = ? WHERE id = ?",
                username, password, id);
    }
}
