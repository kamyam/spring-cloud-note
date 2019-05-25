package com.ky.note.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/test/ribbon")
    public ResponseEntity<String> testRibbon() {

        String requestUrl = "http://eureka-client/info";

        ResponseEntity<String> entity = restTemplate.getForEntity(requestUrl, String.class);
        return entity;
    }
}
