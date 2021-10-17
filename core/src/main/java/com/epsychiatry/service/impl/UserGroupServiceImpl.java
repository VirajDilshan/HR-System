package com.epsychiatry.service.impl;

import com.epsychiatry.model.management.UserGroup;
import com.epsychiatry.repository.UserGroupRepository;
import com.epsychiatry.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Override
    public UserGroup saveUserGroup(UserGroup userGroup) {
        return userGroupRepository.save(userGroup);
    }

    @Override
    public UserGroup saveGroupOnly(UserGroup userGroup) {
        return userGroupRepository.saveGroupOnly(userGroup);
    }

    @Override
    public UserGroup getUserGroupByName(String name) {
        return userGroupRepository.findByName(name);
    }

    @Override
    public List<UserGroup> getAllGroups() {
        return userGroupRepository.findAll();
    }

    @Override
    public Optional<UserGroup> getUserGroupById(Long id) {
        return userGroupRepository.findById(id);
    }

    @Override
    public void activateGroup(Long id) {
        userGroupRepository.activateGroup(id);
    }

    @Override
    public void deactivateGroup(Long id) {
        userGroupRepository.deactivateGroup(id);
    }

    @Override
    public void  deleteUserGroupById(Long id) {
        userGroupRepository.deleteGroupById(id);
    }
}
