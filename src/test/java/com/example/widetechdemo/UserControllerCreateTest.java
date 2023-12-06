package com.example.widetechdemo;

import com.example.widetechdemo.constants.ErrorCodeEnum;
import com.example.widetechdemo.dao.repository.UserRepository;
import com.example.widetechdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerCreateTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateUserSuccess() throws Exception {
        // Mocking the service layer behavior
        Mockito.doNothing().when(userService).createUser(Mockito.any());

        // Sending a request to create a user
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .content("{\"username\":\"test\",\"email\":\"test@example.com\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreateUserFailure() throws Exception {
        // Mocking the service layer behavior to return null (which indicates failure)
        Mockito.doNothing().when(userService).createUser(Mockito.any());

        // Sending a request to create a user
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .content("{\"username\":\"test\",\"email\":\"test@example.com\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ErrorCodeEnum.USER_EXISTED.getCode()));
    }

}
