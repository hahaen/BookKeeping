package com.hahaen.bookkeeping.manager;

import com.hahaen.bookkeeping.converter.p2c.UserInfoP2CConverter;
import com.hahaen.bookkeeping.dao.UserInfoDao;
import com.hahaen.bookkeeping.exception.ResourceNotFoundException;
import com.hahaen.bookkeeping.model.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class UserInfoManagerTest {

    private UserInfoManager userInfoManager;

    @Mock
    private UserInfoDao userInfoDAO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userInfoManager = new UserInfoManagerImpl(userInfoDAO, new UserInfoP2CConverter());
    }

    @Test
    public void testGetUserInfoByUserId() {

        // Arrange
        val userId = 1L;
        val userName = "hardcore";
        val passWord = "hardcore";
        val createTime = LocalDate.now();

        val userInfo = UserInfo.builder()
                .id(userId)
                .username(userName)
                .password(passWord)
                .createTime(createTime)
                .build();

        doReturn(userInfo).when(userInfoDAO).getUserInfoById(userId);

        //Act
        val result = userInfoManager.getUserInfoByUserId(userId);

        //Assert

        //Junit5
//        assertEquals(userId, result.getId());
//        assertEquals("hardcore", result.getUsername());
//        assertEquals("hardcore", result.getPassword());

        //Assert3
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", userName)
                .hasFieldOrPropertyWithValue("password", passWord);

        verify(userInfoDAO).getUserInfoById(userId);

    }

    @Test
    public void testGetUserInfoByUserIdWithInvalidUserId() {

        // Arrange
        val userId = -1L;

        doReturn(null).when(userInfoDAO).getUserInfoById(userId);

        //Act & Assert
        assertThrows(ResourceNotFoundException.class
                , () -> userInfoManager.getUserInfoByUserId(userId));

        verify(userInfoDAO).getUserInfoById(userId);

    }
}
