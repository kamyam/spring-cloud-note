package com.ky.note.consule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 使用@EnableEurekaClient或@EnableDiscoveryClient都可以，非Eureka建议是使用@EnableDiscoveryClient
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulClientApplication.class, args);
    }
}
