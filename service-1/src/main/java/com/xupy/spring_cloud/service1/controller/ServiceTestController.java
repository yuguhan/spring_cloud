package com.xupy.spring_cloud.service1.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ServiceTestController {

    @PostMapping("/sayHello")
    public Mono<String> sayHello(@RequestBody Mono<String> mono, ServerHttpRequest request){
        HttpHeaders httpHeaders = request.getHeaders();
        StringBuilder sb = new StringBuilder();
        List<String> from = httpHeaders.get("from");
        if (!CollectionUtils.isEmpty(from)) {
            sb.append(from.get(0));
        }
        return mono.map(name ->
                sb.append("，来了！").append("你好").append(name).toString());
    }

}