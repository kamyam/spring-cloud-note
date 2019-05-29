package com.ky.note.eureka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * 1.
 * 2. @EnableDiscoveryClient：使用@EnableEurekaClient或@EnableDiscoveryClient都可以;当注册中心非Eureka时必需使用@EnableDiscoveryClient;
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }
}
