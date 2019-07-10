package com.xupy.spring_cloud.service1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@RequestMapping(value = "/api/child")
public class ChildController {
    @PostMapping("/getName")
    public Mono<String> getName(){
        return Mono.just("child");
    }
}
