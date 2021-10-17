package com.epsychiatry.repository;

import com.epsychiatry.model.management.UserGroup;
import com.epsychiatry.repository.custom.UserGroupCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository  extends JpaRepository<UserGroup, Long>, UserGroupCustomRepository {
    UserGroup findByName(String name);
}
