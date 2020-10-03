package com.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CircuitBreakerController {

    @GetMapping("/defaultCurrency")
    public Map<String,String> currencies(){
        Map<String,String> data=new HashMap<>();
        data.put("message","defaut Currencies");
        data.put("currencies","MAD, EUR, USD" );
        return data;
    }
}
