package com.hahaen.bookkeeping.converter.c2s;

import com.google.common.base.Converter;
import com.hahaen.bookkeeping.model.common.UserInfo;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserInfoC2SConverter extends Converter<UserInfo,
        com.hahaen.bookkeeping.model.service.UserInfo> {


    @Override
    protected com.hahaen.bookkeeping.model.service.UserInfo doForward(UserInfo userInfo) {
        return com.hahaen.bookkeeping.model.service.UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.hahaen.bookkeeping.model.service.UserInfo userInfo) {
        return UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }
}
