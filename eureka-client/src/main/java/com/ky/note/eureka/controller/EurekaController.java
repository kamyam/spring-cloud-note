package com.ky.note.eureka.controller;

import com.ky.note.eureka.client.UserClient;
import com.ky.user.api.dto.UserInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/eureka")
public class EurekaController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DiscoveryClient client;
    @Autowired
    private Environment environment;
    @Autowired
    private UserClient userClient;



    @GetMapping("/user/{userId}")
    public UserInfoDTO info(@PathVariable String userId) {
        String port = environment.getProperty("server.port");
        log.info("当前的服务端口:{}", port);
        return userClient.user(userId);
    }

}