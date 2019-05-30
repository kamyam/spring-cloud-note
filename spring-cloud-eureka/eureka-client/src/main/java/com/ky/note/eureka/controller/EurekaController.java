package com.ky.note.eureka.controller;

import com.ky.note.eureka.client.UserClient;
import com.ky.note.eureka.properties.TestProperties;
import com.ky.user.api.dto.UserInfoDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/eureka")
public class EurekaController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private Environment environment;
    @Autowired
    private UserClient userClient;
    @Autowired
    private TestProperties testProperties;

    @GetMapping("/info")
    public List<ServiceInstance> getEurekaInfo() {

        log.info("读取配置：{}", testProperties.getNote());
        String name = environment.getProperty("spring.application.name");
        List<ServiceInstance> instances = discoveryClient.getInstances(name);
        log.info("{}:当前的执行的线程", Thread.currentThread().getName());
        return instances;
    }


    @HystrixCommand(fallbackMethod = "getUserInfoDefault")
    @GetMapping("/user/{userId}")
    public UserInfoDTO getUserInfo(@PathVariable String userId) {
        String port = environment.getProperty("server.port");
        log.info("{}:当前的服务端口:{}", Thread.currentThread().getName(), port);
        return userClient.user(userId);
    }


    public UserInfoDTO getUserInfoDefault(String userId) {
        log.info("{}:进入服务降级方法", Thread.currentThread().getName());
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId("0");
        userInfoDTO.setUsername("系统异常");
        return userInfoDTO;
    }

}