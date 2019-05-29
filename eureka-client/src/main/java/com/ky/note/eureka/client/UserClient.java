package com.ky.note.eureka.client;

import com.ky.user.api.UserServiceUrl;
import com.ky.user.api.client.IUserClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(UserServiceUrl.SERVICE_NAME)
public interface UserClient extends IUserClient {

}
