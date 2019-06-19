package com.ky.note.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kamyam
 * @date 2019/6/18 21:11
 */
@RestController
public class TestController {

    @RequestMapping("/api/test")
    public String test1(){
        return "OK!";
    }

    @RequestMapping("/test")
    public String test2(){
        return "test!";
    }
}
