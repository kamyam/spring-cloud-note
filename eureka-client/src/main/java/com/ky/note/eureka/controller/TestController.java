package com.ky.note.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TestController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DiscoveryClient client;
    @Autowired
    private Environment environment;

    @GetMapping("/info")
    public String info() {
        String port = environment.getProperty("server.port");
        log.info("当前的服务端口:{}", port);
        int a = 1 / (LocalDateTime.now().getSecond() & 2);

        return port;

    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}