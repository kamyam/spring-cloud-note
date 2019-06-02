package com.ky.note.apollo.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ky.note.apollo.dto.TestJavaConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApolloController {

    @Value("${kaamyam.text:error}")
    private String text;

    @Value("${apollo.text:error}")
    private String apolloText;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getApolloText() {
        return apolloText;
    }

    public void setApolloText(String apolloText) {
        this.apolloText = apolloText;
    }

    @RequestMapping("/test")
    public String test() {
        Config config = ConfigService.getConfig("test.yml");
        System.out.println(text);
        System.out.println(apolloText);
        return text + "---" + apolloText;
    }
}
