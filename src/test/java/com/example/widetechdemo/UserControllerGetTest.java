package com.example.widetechdemo;

import com.example.widetechdemo.constants.ErrorCodeEnum;
import com.example.widetechdemo.controller.UserController;
import com.example.widetechdemo.dao.entity.User;
import com.example.widetechdemo.dto.request.User.QueryUserReq;
import com.example.widetechdemo.dto.response.RestResp;
import com.example.widetechdemo.dto.response.Status;
import com.example.widetechdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ComponentScan("com.example.widetechdemo")
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerGetTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    public void getUserById_existingUser_shouldReturnUser() {
        // Arrange
        int userId = 1;
        QueryUserReq request = new QueryUserReq(userId);
        User mockUser = new User("John Doe", "john@example.com");
        when(userService.getUserById(userId)).thenReturn(mockUser);

        // Act
        RestResp<User> response = userController.getUserById(request);

        // Assert
        assertNotNull(response);
        assertEquals(Status.SUCCESS, response.getStatus());
        assertNotNull(response.getData());
        assertEquals(mockUser, response.getData());

        // Verify interactions with the mock
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void getUserById_nonExistingUser_shouldReturnError() {
        // Arrange
        int userId = 1;
        QueryUserReq request = new QueryUserReq(userId);
        when(userService.getUserById(userId)).thenReturn(null);

        // Act
        RestResp<User> response = userController.getUserById(request);

        // Assert
        assertNotNull(response);
        assertEquals(Status.FAIL, response.getStatus());
        assertNull(response.getData());
        assertEquals(ErrorCodeEnum.USER_NOT_EXIST.getCode(), response.getCode());

        // Verify interactions with the mock
        verify(userService, times(1)).getUserById(userId);
    }

}


