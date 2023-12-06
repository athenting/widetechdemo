package com.example.widetechdemo.controller;

import com.example.widetechdemo.Exception.BusinessException;
import com.example.widetechdemo.constants.ErrorCodeEnum;
import com.example.widetechdemo.dao.entity.User;
import com.example.widetechdemo.dto.request.User.CreateUserReq;
import com.example.widetechdemo.dto.request.User.DelUserReq;
import com.example.widetechdemo.dto.request.User.QueryUserReq;
import com.example.widetechdemo.dto.request.User.UpdateUserReq;
import com.example.widetechdemo.dto.response.RestResp;
import com.example.widetechdemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "user single query api")
    public RestResp<User> getUserById(@RequestBody QueryUserReq req) {
        User user = userService.getUserById(req.getUserId());
        return user != null ? RestResp.ok(user) : RestResp.fail(ErrorCodeEnum.USER_NOT_EXIST, null);
    }

    @PostMapping
    @Operation(summary = "create new user api")
    public RestResp<Void> createUser(@RequestBody CreateUserReq req) {

        User createdUser = new User(req.getUsername(), req.getEmail());

        try {
            userService.createUser(createdUser);
        } catch (BusinessException e) {
            return RestResp.fail(ErrorCodeEnum.USER_EXISTED);
        }

        return RestResp.ok();
    }

    @PutMapping("/{id}")
    @Operation(summary = "user update api")
    public RestResp<User> updateUser(@RequestBody UpdateUserReq req) {
        User user = new User(req.getUsername(), req.getEmail());

        User updatedUser = userService.updateUser(req.getUserId(), user);

        return updatedUser != null ? RestResp.ok(updatedUser) : RestResp.fail(ErrorCodeEnum.USER_NOT_EXIST, null);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "user update api")
    public RestResp<Void> deleteUser(@RequestBody DelUserReq req) {
        try {
            userService.deleteUser(req.getUserId());
        } catch (BusinessException e) {
            return RestResp.fail(ErrorCodeEnum.USER_NOT_EXIST);
        }
        return RestResp.ok();
    }
}
