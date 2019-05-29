package com.ky.user.core.controller;

import com.ky.user.api.client.IUserClient;
import com.ky.user.api.dto.UserInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserClient {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserInfoDTO user(String userId) {
        log.info(Thread.currentThread().getName() + ": 获取用户信息");
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId(userId);
        userInfoDTO.setSex(1);
        userInfoDTO.setUsername("大牛");

        //测试超时
        if (userId.equals("1")) {
            try {
                Thread.sleep(1000 * 2L);
            } catch (InterruptedException e) {
            }
        }
        return userInfoDTO;
    }


}
