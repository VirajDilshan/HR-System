package com.epsychiatry.repository.custom;

import com.epsychiatry.model.management.UserGroup;

public interface UserGroupCustomRepository {
    UserGroup saveGroupOnly(UserGroup userGroup);
    void activateGroup(Long id);
    void deactivateGroup(Long id);
    void deleteGroupById(Long id);
}
