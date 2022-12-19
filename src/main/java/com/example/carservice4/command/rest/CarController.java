package com.example.carservice4.command.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test1")
public class CarController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }


}
