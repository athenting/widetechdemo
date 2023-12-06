package com.example.widetechdemo;

import com.example.widetechdemo.Exception.BusinessException;
import com.example.widetechdemo.constants.ErrorCodeEnum;
import com.example.widetechdemo.controller.UserController;
import com.example.widetechdemo.dao.entity.User;
import com.example.widetechdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerUpdateTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testUpdateUserSuccess() throws Exception {
        // Mocking the service layer behavior for a successful update
        User updatedUser = new User("updatedUser", "updated@example.com");
        Mockito.when(userService.updateUser(Mockito.anyInt(), Mockito.any(User.class))).thenReturn(updatedUser);

        // Sending a request to update a user
        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/1")
                        .content("{\"username\":\"updatedUser\",\"email\":\"updated@example.com\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ErrorCodeEnum.OK.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("updatedUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("updated@example.com"));
    }

    @Test
    public void testUpdateUserFailure() throws Exception {
        // Mocking the service layer behavior to throw an exception
        Mockito.when(userService.updateUser(Mockito.anyInt(), Mockito.any(User.class)))
                .thenThrow(new BusinessException("User not found"));

        // Sending a request to update a user
        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/1")
                        .content("{\"username\":\"updatedUser\",\"email\":\"updated@example.com\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ErrorCodeEnum.USER_NOT_EXIST.getCode()));
    }
}
