package com.epsychiatry.repository;

import com.epsychiatry.model.management.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    Module findByRolePrefix(String rolePrefix);
}
