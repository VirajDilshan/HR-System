package com.epsychiatry.service;

import com.epsychiatry.model.management.UserGroup;

import java.util.List;
import java.util.Optional;

public interface UserGroupService {
    UserGroup saveUserGroup (UserGroup userGroup);
    UserGroup saveGroupOnly(UserGroup userGroup);
    UserGroup getUserGroupByName(String name);
    List<UserGroup> getAllGroups();
    Optional<UserGroup> getUserGroupById(Long id);
    void deleteUserGroupById(Long id);
    void activateGroup(Long id);
    void deactivateGroup(Long id);
}
