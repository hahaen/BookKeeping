package com.hahaen.bookkeeping.converter;

import com.hahaen.bookkeeping.converter.c2s.UserInfoC2SConverter;
import com.hahaen.bookkeeping.model.common.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class UserInfoC2SConverterTest {

    private UserInfoC2SConverter userInfoC2SConverter =
            new UserInfoC2SConverter();

    @Test
    public void testDoForward() {
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

        //Act

        val result = userInfoC2SConverter.convert(userInfoCommon);

        //Assert
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", userName)
                .hasFieldOrPropertyWithValue("password", passWord);

    }

    @Test
    public void testDoBackward() {
        //Arrange
        val userId = 100L;
        val userName = "hardcore";
        val passWord = "hardcore";

        val userInfoService = com.hahaen.bookkeeping.model.service.UserInfo.builder()
                .id(userId)
                .username(userName)
                .password(passWord)
                .build();

        //Act

        val result = userInfoC2SConverter.reverse().convert(userInfoService);

        //Assert
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", userName)
                .hasFieldOrPropertyWithValue("password", passWord);

    }

}
