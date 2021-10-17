package com.epsychiatry.service.impl;

import com.epsychiatry.model.management.Module;
import com.epsychiatry.repository.ModuleRepository;
import com.epsychiatry.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;
    @Override
    public Module saveModule(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    @Override
    public Module getModuleByRolePrefix(String rolePrefix) {
        return moduleRepository.findByRolePrefix(rolePrefix);
    }
}
