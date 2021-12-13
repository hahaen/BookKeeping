package com.hahaen.bookkeeping.dao;

import com.hahaen.bookkeeping.dao.mapper.UserInfoMapper;
import com.hahaen.bookkeeping.model.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserInfoDAOTest {

    @Mock
    private UserInfoMapper userInfoMapper;

    @InjectMocks
    private UserInfoDaoImpl userInfoDAO;

    @Test
    public void testGetUserInfoById() {
        //Arrange
        val userId = 100L;
        val userName = "hardcore";
        val passWord = "hardcore";
        val createTime = LocalDate.now();

        val userInfo = UserInfo.builder()
                .id(userId)
                .username(userName)
                .password(passWord)
                .createTime(createTime)
                .build();

        doReturn(userInfo).when(userInfoMapper).getUserInfoByUserId(userId);

        //Act

        val result = userInfoDAO.getUserInfoById(userId);

        //Assert
        assertEquals(userInfo, result);

        verify(userInfoMapper).getUserInfoByUserId(userId);

    }
}
