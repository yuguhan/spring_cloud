package com.xupy.spring_cloud.service1.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
//        return Mono.error(new RuntimeException());
    }

    @GetMapping("/aligenie/705fc1d67ee7b0ad2a52828285ec34c8.txt")
    public Mono<String> sign(){
        return Mono.just("Jfc4Z4Ur15JwUBuvUQD5wg7Nu8+l+HscqYlfofbyJdayxyKeAEgCdKA7OskwikbA");
    }

    @GetMapping("/aligenie/404e0abc742f39d7a21f1570e762f74f.txt")
    public Mono<String> sign2(){
        return Mono.just("Jfc4Z4Ur15JwUBuvUQD5wg7Nu8+l+HscqYlfofbyJdY2cvtjfyBA1KTNBHJaHt4S");
    }

}