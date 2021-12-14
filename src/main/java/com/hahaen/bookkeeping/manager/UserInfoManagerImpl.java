package com.hahaen.bookkeeping.manager;

import com.hahaen.bookkeeping.converter.p2c.UserInfoP2CConverter;
import com.hahaen.bookkeeping.dao.UserInfoDao;
import com.hahaen.bookkeeping.exception.ResourceNotFoundException;
import com.hahaen.bookkeeping.model.common.UserInfo;
import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoManagerImpl implements UserInfoManager {

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
    public void login(String username, String password) {
        //Get subject
        val subejt = SecurityUtils.getSubject();

        //Construct tonken
        val usernamePasswordToken = new UsernamePasswordToken(username, password);

        //login
        subejt.login(usernamePasswordToken);
    }
}
