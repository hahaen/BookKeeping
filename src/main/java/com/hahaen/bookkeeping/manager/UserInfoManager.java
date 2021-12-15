package com.hahaen.bookkeeping.manager;


import com.hahaen.bookkeeping.model.common.UserInfo;

public interface UserInfoManager {
    /**
     * Get user infomation by user id.
     *
     * @param userId the specific user id
     */
    UserInfo getUserInfoByUserId(Long userId);

    /**
     * Get user infomation by user name.
     *
     * @param userName the specific user name.
     */
    UserInfo getUserInfoByUserName(String userName);

    /**
     * Login with username and password.
     *
     * @param username username
     * @param password the related password
     */
    void login(String username, String password);
}
