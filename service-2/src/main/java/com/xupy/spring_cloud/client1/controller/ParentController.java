package com.xupy.spring_cloud.client1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@RequestMapping(value = "/api/parent")
public class ParentController {

    @PostMapping("/getName")
    public Mono<String> getName(){
        return Mono.just("parent");
    }
}
