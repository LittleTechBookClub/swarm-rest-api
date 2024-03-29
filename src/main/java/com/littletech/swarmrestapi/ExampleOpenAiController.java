package com.littletech.swarmrestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Value;


@RestController
public class ExampleOpenAiController {


    @Value("${OPENAI_API_KEY:default_value}") 
    private String apiKey;

    @GetMapping("/example")

    public String example() {
        if (apiKey == null) {
            return "API Key not found";
        }
        System.out.println(apiKey);
        
        return "Hello OpenAI";
    
}
}