package com.hahaen.bookkeeping.manager;


import com.hahaen.bookkeeping.model.common.UserInfo;

public interface UserInfoManager {
    /**
     *Get user infomation by user id
     * @param UserId the specific user id
     *
     */
    UserInfo getUserInfoByUserId(Long UserId);
}
