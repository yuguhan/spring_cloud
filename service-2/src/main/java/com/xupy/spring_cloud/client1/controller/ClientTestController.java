package com.xupy.spring_cloud.client1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ClientTestController {

    private final WebClient.Builder clientBuilder;

    @Autowired
    public ClientTestController(WebClient.Builder clientBuilder) {
        this.clientBuilder = clientBuilder;
    }

    @GetMapping("/sayHello/{name}")
    public Mono<String> sayHello(@PathVariable String name){

        return clientBuilder
                .baseUrl("http://service-1/sayHello")//指定url，service-1是我们注册的微服务applications.name
                .build()
                .method(HttpMethod.POST)//post请求
                .syncBody(name)//参数 Object类型
                .header("from", "client-1")//header 部分的内容
                .retrieve()//请求结果的方法
                .bodyToMono(String.class);//将结果转换为相应的类型，这是String，直接返回即可

    }
}
