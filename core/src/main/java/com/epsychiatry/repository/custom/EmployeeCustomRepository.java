package com.epsychiatry.repository.custom;

public interface EmployeeCustomRepository {
    void activateEmployee(Long id);
    void deactivateEmployee(Long id);
    void saveAuth(Long id, String username, String password, boolean enabled);
    void saveUserAuth(Long id, String username, String password);
}
