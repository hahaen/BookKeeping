package com.hahaen.bookkeeping.converter.p2c;

import com.hahaen.bookkeeping.model.persistence.UserInfo;
import com.google.common.base.Converter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserInfoP2CConverter extends Converter<UserInfo, com.hahaen.bookkeeping.model.common.UserInfo> {


    @Override
    protected com.hahaen.bookkeeping.model.common.UserInfo doForward(UserInfo userInfo) {
        return com.hahaen.bookkeeping.model.common.UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.hahaen.bookkeeping.model.common.UserInfo userInfo) {
        return UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }
}
