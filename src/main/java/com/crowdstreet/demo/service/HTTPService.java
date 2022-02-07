package com.crowdstreet.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class HTTPService {
    
    private static WebClient webClient = WebClient.create();

    public String post(String url, String body){
        String result = webClient
            .post()
            .uri(url)
            .body(BodyInserters.fromValue(body))
            .exchangeToMono(response -> response.bodyToMono(String.class))
            .block();
        return result;
    }
}
