package com.ky.note.eureka.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;



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
