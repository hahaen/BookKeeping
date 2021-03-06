package com.hahaen.bookkeeping.controller;

import com.hahaen.bookkeeping.converter.c2s.UserInfoC2SConverter;
import com.hahaen.bookkeeping.exception.InvalidParameterException;
import com.hahaen.bookkeeping.manager.UserInfoManager;
import com.hahaen.bookkeeping.model.service.UserInfo;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("v1.0/users")
@Slf4j
public class UserController {

    private final UserInfoManager userInfoManager;
    private final UserInfoC2SConverter userInfoC2SConverter;

    @Autowired
    public UserController(final UserInfoManager userInfoManager,
                          final UserInfoC2SConverter userInfoC2SConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoC2SConverter = userInfoC2SConverter;
    }

    /**
     * Get user information by specifid user id.
     *
     * @param userId the user id
     * @return user info response entity.
     */

    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getUserInfoByUserId(@PathVariable("id") @NotNull Long userId) {
        log.debug("Get user info by user id {}", userId);
        if (userId <= 0L) {
            throw new InvalidParameterException(String.format("The user %s is invalid", userId));
        }
        val userInfo = userInfoManager.getUserInfoByUserId(userId);
        return ResponseEntity.ok(userInfoC2SConverter.convert(userInfo));
    }

    @PostMapping()
    public ResponseEntity<UserInfo> register(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        val userInfo = userInfoManager.register(username, password);
        return ResponseEntity.ok(userInfoC2SConverter.convert(userInfo));
    }
}

