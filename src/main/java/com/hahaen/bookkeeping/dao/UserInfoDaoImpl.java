package com.hahaen.bookkeeping.dao;

import com.hahaen.bookkeeping.dao.mapper.UserInfoMapper;
import com.hahaen.bookkeeping.model.persistence.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoDaoImpl implements UserInfoDao {

    private final UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfoById(Long id) {
        return userInfoMapper.getUserInfoByUserId(id);
    }

    @Override
    public UserInfo getUserInfoByUserName(String userName) {
        return userInfoMapper.getUserInfoByUserName(userName);
    }

    @Override
    public void createNewUser(String username, String password) {

    }

}

