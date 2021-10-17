package com.epsychiatry.repository;

import com.epsychiatry.model.management.Employee;
import com.epsychiatry.repository.custom.EmployeeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeCustomRepository {
    Employee findEmployeesByUsername(String username);
    Employee findEmployeesByEmpID(String id);
    Employee findEmployeesByNic(String nic);

    @Query("SELECT e FROM Employee e WHERE e.id != ?1 AND e.username = ?2")
    List<Employee> findEmployeesByUsernameForUpdateAuth(Long id, String username);
}
