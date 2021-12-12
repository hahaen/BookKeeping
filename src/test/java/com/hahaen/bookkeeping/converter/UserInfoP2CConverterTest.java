package com.hahaen.bookkeeping.converter;

import com.hahaen.bookkeeping.converter.p2c.UserInfoP2CConverter;
import com.hahaen.bookkeeping.model.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class UserInfoP2CConverterTest {

    private UserInfoP2CConverter userInfoP2CConverter =
            new UserInfoP2CConverter();

    @Test
    public void testDoForward() {
        //Arrange
        val userId = 100L;
        val userName = "hardcore";
        val passWord = "hardcore";
        val createTime = LocalDate.now();

        val userInfoPersistence = UserInfo.builder()
                .id(userId)
                .username(userName)
                .password(passWord)
                .createTime(createTime)
                .build();

        //Act

        val result = userInfoP2CConverter.convert(userInfoPersistence);

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

        val userInfoCommon = com.hahaen.bookkeeping.model.common.UserInfo.builder()
                .id(userId)
                .username(userName)
                .password(passWord)
                .build();

        //Act

        val result = userInfoP2CConverter.reverse().convert(userInfoCommon);

        //Assert
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", userName)
                .hasFieldOrPropertyWithValue("password", passWord)
                .hasFieldOrPropertyWithValue("createTime", null);

    }
}
