package com.ky.note.eureka.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 注解@RefreshScope开启热更新，需要引入actuator包，调用127.0.0.1:5301/actuator/refresh接口即可
 */
@RefreshScope
@ConfigurationProperties("ky")
public class TestProperties {

    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
