package com.ank.ss_action16.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping("/product")
public class ProductController {

    private Logger logger =
            Logger.getLogger(ProductController.class.getName());


    @GetMapping("/main")
    public String main() {
        return "main.html";
    }

    //csrf protection enabled
    @PostMapping("/add")
    public String add(@RequestParam String name) {
        logger.info("Adding product " + name);
        return "add.html";
    }

    //csrf protection enabled
    @PostMapping("/hello")
    public String hello() {
        logger.info("Calling from hello endpoint ");
        return "hello.html";
    }


}
