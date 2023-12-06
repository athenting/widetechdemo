package com.example.widetechdemo.dao.repository;

import com.example.widetechdemo.dao.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Custom queries or methods if needed
}
