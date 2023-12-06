package com.example.widetechdemo.controller;

import com.example.widetechdemo.Exception.BusinessException;
import com.example.widetechdemo.constants.ErrorCodeEnum;
import com.example.widetechdemo.dao.entity.Employee;
import com.example.widetechdemo.dto.request.Employee.UpdateEmployeeReq;
import com.example.widetechdemo.dto.response.RestResp;
import com.example.widetechdemo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "employee single query api")
    public RestResp<Employee> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee != null ? RestResp.ok(employee) : RestResp.fail(ErrorCodeEnum.USER_NOT_EXIST, null);
    }

    @PostMapping
    @Operation(summary = "create new employee api")
    public RestResp<Void> createEmployee(@RequestBody Employee req) {
        Employee createdEmp = new Employee(req.getName(), req.getPosition(), req.getDepartment());

        try {
            employeeService.createEmployee(createdEmp);
        } catch (BusinessException e) {
            return RestResp.fail(ErrorCodeEnum.USER_EXISTED);
        }

        return RestResp.ok();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update existed employee api")
    public RestResp<Employee> updateEmployee(@RequestBody UpdateEmployeeReq req) {
        Employee employee = new Employee(req.getName(), req.getPosition(), req.getDept());
        Employee updatedEmployee = employeeService.updateEmployee(req.getId(), employee);
        return updatedEmployee != null ? RestResp.ok(updatedEmployee) : RestResp.fail(ErrorCodeEnum.USER_NOT_EXIST, null);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete employee api")
    public RestResp<Void> deleteEmployee(@PathVariable int id) {
        try {
            employeeService.deleteEmployee(id);
        } catch (BusinessException e) {
            return RestResp.fail(ErrorCodeEnum.USER_NOT_EXIST);
        }
        return RestResp.ok();
    }
}
