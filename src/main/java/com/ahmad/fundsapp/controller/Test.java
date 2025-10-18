package com.ahmad.fundsapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/Test")
    public String test() {
        return "OK";
    }
}