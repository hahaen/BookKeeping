package com.hahaen.bookkeeping.converter.p2c;

import com.google.common.base.Converter;
import com.hahaen.bookkeeping.model.persistence.UserInfo;
import com.sun.istack.internal.NotNull;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserInfoP2CConverter extends Converter<UserInfo, com.hahaen.bookkeeping.model.common.UserInfo> {


    @Override
    protected com.hahaen.bookkeeping.model.common.UserInfo doForward(@NotNull UserInfo userInfo) {
        return com.hahaen.bookkeeping.model.common.UserInfo.builder()
            .id(userInfo.getId())
            .username(userInfo.getUsername())
            .password(userInfo.getPassword())
            .build();
    }

    @Override
    protected UserInfo doBackward(@NotNull com.hahaen.bookkeeping.model.common.UserInfo userInfo) {
        return UserInfo.builder()
            .id(userInfo.getId())
            .username(userInfo.getUsername())
            .password(userInfo.getPassword())
            .build();
    }
}
