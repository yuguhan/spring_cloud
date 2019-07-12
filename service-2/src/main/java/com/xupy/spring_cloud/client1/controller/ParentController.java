package com.xupy.spring_cloud.client1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/parent")
public class ParentController {

    @PostMapping("/getName")
    public Mono<String> getName(){
        return Mono.just("parent");
    }
}
