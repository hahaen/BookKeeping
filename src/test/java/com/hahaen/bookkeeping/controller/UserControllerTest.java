package com.hahaen.bookkeeping.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.hahaen.bookkeeping.converter.c2s.UserInfoC2SConverter;
import com.hahaen.bookkeeping.exception.GlobalExceptionHandler;
import com.hahaen.bookkeeping.manager.UserInfoManager;
import com.hahaen.bookkeeping.model.common.UserInfo;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserInfoManager userInfoManager;

    @Mock
    private UserInfoC2SConverter userInfoC2SConverter;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @AfterEach
    public void teadown() {
        reset(userInfoManager);
        reset(userInfoC2SConverter);
    }

    @Test
    public void testGetUserInfoByUserId() throws Exception {
        //Arrange
        val userId = 100L;
        val userName = "hardcore";
        val passWord = "hardcore";
        val createTime = LocalDate.now();

        val userInfoCommon = UserInfo.builder()
                .id(userId)
                .username(userName)
                .password(passWord)
                .build();

        val userInfoService = com.hahaen.bookkeeping.model.service.UserInfo.builder()
                .id(userId)
                .username(userName)
                .password(passWord)
                .build();

        doReturn(userInfoCommon).when(userInfoManager).getUserInfoByUserId(anyLong());
        doReturn(userInfoService).when(userInfoC2SConverter).convert(userInfoCommon);

        //art & assert
        mockMvc.perform(get("/v1.0/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"id\":100,\"username\":\"hardcore\",\"password\":\"hardcore\"}"));

        verify(userInfoManager).getUserInfoByUserId(anyLong());
        verify(userInfoC2SConverter).convert(userInfoCommon);

    }

    @Test
    public void testGetUserInfoByUserIdWithInvalidUserId() throws Exception {
        //Arrange
        val userId = -100L;

        //art & assert
        mockMvc.perform(get("/v1.0/users/" + userId))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"code\":\"INVALID_PARAMETER\",\"errorType\":\"Client\",\"message\":\"The user -100 is invalid\",\"statusCode\":400}"));

        verify(userInfoManager, never()).getUserInfoByUserId(anyLong());

    }

}
