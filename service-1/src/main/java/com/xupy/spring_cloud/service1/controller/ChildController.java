package com.xupy.spring_cloud.service1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/child")
public class ChildController {

    @PostMapping("/getName")
    public Mono<String> getName(){
        return Mono.just("child");
    }
}
