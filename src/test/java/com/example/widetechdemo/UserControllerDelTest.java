package com.example.widetechdemo;

import com.example.widetechdemo.Exception.BusinessException;
import com.example.widetechdemo.constants.ErrorCodeEnum;
import com.example.widetechdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerDelTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testDeleteUserSuccess() throws Exception {
        // Mocking the service layer behavior for a successful deletion
        Mockito.doNothing().when(userService).deleteUser(Mockito.anyInt());

        // Sending a request to delete a user
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ErrorCodeEnum.OK.getCode()));
    }

    @Test
    public void testDeleteUserFailure() throws Exception {
        // Mocking the service layer behavior to throw an exception
        Mockito.doThrow(new BusinessException("User not found")).when(userService).deleteUser(Mockito.anyInt());

        // Sending a request to delete a user
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ErrorCodeEnum.USER_NOT_EXIST.getCode()));
    }
}
