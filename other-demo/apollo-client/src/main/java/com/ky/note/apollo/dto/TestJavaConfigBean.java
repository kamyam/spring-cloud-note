package com.ky.note.apollo.dto;

import org.springframework.beans.factory.annotation.Value;

public class TestJavaConfigBean {
    @Value("${apollo:error}")
    private String apolloText;

    public String getApolloText() {
        return apolloText;
    }

    public void setApolloText(String apolloText) {
        this.apolloText = apolloText;
    }
}
