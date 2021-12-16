package com.hahaen.bookkeeping.manager;

import com.hahaen.bookkeeping.converter.p2c.UserInfoP2CConverter;
import com.hahaen.bookkeeping.dao.UserInfoDao;
import com.hahaen.bookkeeping.exception.InvalidParameterException;
import com.hahaen.bookkeeping.exception.ResourceNotFoundException;
import com.hahaen.bookkeeping.model.common.UserInfo;
import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserInfoManagerImpl implements UserInfoManager {

    public static final int HASH_ITERATIONS = 1000;
    private final UserInfoDao userInfoDao;
    private final UserInfoP2CConverter userInfoP2CConverter;

    @Autowired
    public UserInfoManagerImpl(final UserInfoDao userInfoDao,
                               final UserInfoP2CConverter userInfoP2CConverter) {
        this.userInfoDao = userInfoDao;
        this.userInfoP2CConverter = userInfoP2CConverter;
    }

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        return Optional.ofNullable(userInfoDao.getUserInfoById(userId))
            .map(userInfoP2CConverter::convert)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format("User %s was not found", userId)));
    }

    @Override
    public UserInfo getUserInfoByUserName(String userName) {
        return Optional.ofNullable(userInfoDao.getUserInfoByUserName(userName))
            .map(userInfoP2CConverter::convert)
            .orElseThrow(() -> new ResourceNotFoundException(
                String.format("User name %s was not found", userName)));
    }

    @Override
    public void login(String username, String password) {
        //Get subject
        val subejt = SecurityUtils.getSubject();

        //Construct tonken
        val usernamePasswordToken = new UsernamePasswordToken(username, password);

        //login
        subejt.login(usernamePasswordToken);
    }

    @Override
    public UserInfo register(String username, String password) {
        val userInfo = userInfoDao.getUserInfoByUserName(username);
        if (userInfo != null) {
            throw new InvalidParameterException(
                String.format("The user %s was registered", username));
        }

        //Set ramdom salt
        String salt = UUID.randomUUID().toString();
        String encryptedPassword = new Sha256Hash(password, salt, HASH_ITERATIONS).toBase64();

        val newUserInfo = com.hahaen.bookkeeping.model.persistence.UserInfo
            .builder()
            .username(username)
            .password(encryptedPassword)
            .salt(salt)
            .createTime(LocalDate.now())
            .build();
        userInfoDao.createNewUser(newUserInfo);
        return userInfoP2CConverter.convert(newUserInfo);
    }
}
