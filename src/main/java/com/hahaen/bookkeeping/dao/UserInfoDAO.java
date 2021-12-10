package com.hahaen.bookkeeping.dao;

import com.hahaen.bookkeeping.model.persistence.UserInfo;

public interface UserInfoDAO {

    UserInfo getUserInfoById(Long id);

    void createNewUser(String username, String password);
}
