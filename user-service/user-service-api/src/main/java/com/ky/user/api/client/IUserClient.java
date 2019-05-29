package com.ky.user.api.client;

import com.ky.user.api.dto.UserInfoDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IUserClient {

    /**
     * 获取用户信息
     */
    @GetMapping("user/{userId}")
    UserInfoDTO user(@PathVariable String userId);


}
