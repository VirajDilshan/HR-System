package com.epsychiatry.repository.custom.impl;

import com.epsychiatry.model.management.UserGroup;
import com.epsychiatry.repository.custom.UserGroupCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserGroupCustomRepositoryImpl implements UserGroupCustomRepository {

    @Autowired
    private DataSource dataSource;

    public UserGroup saveGroupOnly(UserGroup userGroup) {
        JdbcTemplate template = new JdbcTemplate(dataSource);

        template.update("INSERT INTO user_group (name, description) VALUE " +
                "(?, ?)",
                userGroup.getName(),
                userGroup.getDescription());

        return userGroup;
    }

    @Override
    public void activateGroup(Long id) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("UPDATE user_group SET activate = 1 WHERE id = ?", id);
    }

    @Override
    public void deactivateGroup(Long id) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("UPDATE user_group SET activate = 0 WHERE id = ?", id);
    }

    @Override
    public void deleteGroupById(Long id) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        template.update("DELETE FROM user_group_roles WHERE user_group_id = ?", id);
        template.update("DELETE FROM user_group WHERE id = ?", id);


    }


}
