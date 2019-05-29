package com.ky.user.core.controller;

import com.ky.user.api.client.IUserClient;
import com.ky.user.api.dto.UserInfoDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserClient {

    @Override
    public UserInfoDTO user(String userId) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId(userId);
        userInfoDTO.setSex(1);
        userInfoDTO.setUsername("大牛");
        return userInfoDTO;
    }
}
