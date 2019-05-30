package com.ky.note.eureka;


import com.ky.note.eureka.properties.TestProperties;
import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


/**
 * 0. @SpringCloudApplication:= @EnableCircuitBreaker + @EnableDiscoveryClient + @SpringBootApplication
 * 1. @EnableDiscoveryClient：使用@EnableEurekaClient或@EnableDiscoveryClient都可以;当注册中心非Eureka时必需使用@EnableDiscoveryClient;
 * 2. @EnableFeignClients: 启用Feign自动配置
 * 3. @EnableCircuitBreaker: 启用Hystrix自动配置
 */
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    /**
     * 注解@RefreshScope开启热更新，需要引入actuator包，调用127.0.0.1:5301/actuator/refresh接口即可
     */
    @Bean
    @RefreshScope
    TestProperties testProperties() {
        return new TestProperties();
    }

    /**
     * Feign提供了日志打印的功能，Feign的日志级别分为四种：
     * NONE: 不记录任何信息。
     * BASIC: 仅记录请求方法、URL以及响应状态码和执行时间。
     * HEADERS: 除了记录BASIC级别的信息之外，还会记录请求和响应的头信息。
     * FULL: 记录所有请求与响应的明细，包括头信息、请求体、元数据等。
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


}
