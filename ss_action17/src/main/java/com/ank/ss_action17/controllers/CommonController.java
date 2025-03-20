package com.ank.ss_action17.controllers;

import com.ank.ss_action17.model.EmployeeRecord;
import com.ank.ss_action17.services.CommonServiceRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class CommonController {

    private final CommonServiceRecord commonService;

    private Logger log = Logger.getLogger(CommonController.class.getName());

    public CommonController(CommonServiceRecord commonService) {
        this.commonService = commonService;
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("Calling from controller");
        return "Hello, " + commonService.getName();
    }

    @GetMapping("/book/details/{name}")
    public EmployeeRecord getDetails(@PathVariable String name) {
        return commonService.getBookDetails(name);
    }
}
