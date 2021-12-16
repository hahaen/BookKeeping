package com.hahaen.bookkeeping.dao;

import com.hahaen.bookkeeping.model.persistence.UserInfo;

public interface UserInfoDao {

    UserInfo getUserInfoById(Long id);

    UserInfo getUserInfoByUserName(String userName);

    void createNewUser(UserInfo userInfo);

}
