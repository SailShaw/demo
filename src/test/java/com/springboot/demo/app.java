package com.springboot.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class app {

    @RequestMapping("/")
    public String Hello(){
        return "Hello Spring Boot!";


    }

    public static void main(String[] args) {


        SpringApplication.run(app.class,args);
    }

}
