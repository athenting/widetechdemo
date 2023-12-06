package com.example.widetechdemo.service.impl;

import com.example.widetechdemo.Exception.BusinessException;
import com.example.widetechdemo.dao.entity.Employee;
import com.example.widetechdemo.dao.entity.User;
import com.example.widetechdemo.dao.repository.EmployeeRepository;
import com.example.widetechdemo.service.EmployeeService;
import com.example.widetechdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void createEmployee(Employee employee) {
        try {
            employeeRepository.save(employee);
            logger.info("employee created successfully: {}", employee.getName());
        } catch (DataIntegrityViolationException e) {
            // 处理数据库唯一约束异常
            logger.error("Failed to create employee. name '{}' already exists.", employee.getName());
            throw new BusinessException("employee already exists.", e);
        }
    }

    @Override
    @Transactional
    public Employee updateEmployee(int id, Employee employee) {
        Optional<Employee> Employee = employeeRepository.findById(id);
        if (Employee.isPresent()) {
            Employee updatedEmp = Employee.get();
            updatedEmp.setName(employee.getName());
            updatedEmp.setPosition(employee.getPosition());
            updatedEmp.setDepartment(employee.getDepartment());

            return employeeRepository.save(updatedEmp);
        } else {
            logger.warn("Employee with ID {} not found. Update operation failed.", id);
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        Optional<Employee> userToDelete = employeeRepository.findById(id);
        userToDelete.ifPresentOrElse(
                user -> {
                    employeeRepository.delete(user);
                    logger.info("Employee with ID {} deleted successfully.", id);
                },
                () -> {
                    String errorMessage = String.format("Employee with ID %d not found. Delete operation failed.", id);
                    logger.warn(errorMessage);
                    throw new BusinessException(errorMessage);                }
        );
    }
}
