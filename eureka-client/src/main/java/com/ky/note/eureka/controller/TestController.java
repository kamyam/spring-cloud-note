package com.ky.note.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TestController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/info")
    public Map<String, List<String>> info() {

        List<String> services = client.getServices();
        return services.stream().collect(Collectors.toMap(String::toString, x ->
                client.getInstances(x).stream().map(ServiceInstance::getInstanceId).collect(Collectors.toList())));

    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}