package com.example.hellospringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HellospringsecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(HellospringsecurityApplication.class, args);
    }

    @GetMapping("/users")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name){
        return String.format("Hello %s!", name);
    }
}
