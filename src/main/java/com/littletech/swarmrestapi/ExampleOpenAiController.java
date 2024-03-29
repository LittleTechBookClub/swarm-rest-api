package com.littletech.swarmrestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Value;


@RestController
public class ExampleOpenAiController {


    @Value("${OPENAI_API_KEY}")
    private String apiKey;

    @GetMapping("/example")

    public String example() {
        System.out.println(apiKey);
        
        return "Hello OpenAI";
    
}
}