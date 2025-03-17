package com.ank.ss_action13.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/a")
    public String postEndpointA() {
        return "Works /a PostMapping!";
    }

    @GetMapping("/a")
    public String getEndpointA() {
        return "Works /a GetMapping!";
    }

    @GetMapping("/a/b")
    public String getEnpointB() {
        return "Works /a/b GetMapping!";
    }

    @GetMapping("/a/b/c")
    public String getEnpointC() {
        return "Works /a/b/c GetMapping!";
    }

    @GetMapping("/a/b/c/d")
    public String getEnpointD() {
        return "Works /a/b/c/d GetMapping!";
    }

    @GetMapping("/product/{code}")
    public String productCode(@PathVariable String code) {
        return code;
    }


}
