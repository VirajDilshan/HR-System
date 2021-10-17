package com.epsychiatry.service;

import com.epsychiatry.model.management.Module;

import java.util.List;

public interface ModuleService {
    Module saveModule(Module module);
    List<Module> getAllModules();
    Module getModuleByRolePrefix(String rolePrefix);
}
