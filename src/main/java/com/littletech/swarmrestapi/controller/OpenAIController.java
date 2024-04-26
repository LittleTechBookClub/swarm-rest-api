package com.littletech.swarmrestapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.littletech.swarmrestapi.service.OpenAIService;

@RestController
@RequestMapping("/")
public class OpenAIController {

    private final OpenAIService openaiservice;

    public OpenAIController(OpenAIService _openaiservice) {
        this.openaiservice = _openaiservice;
    }

    @PostMapping("/process-inspection")
    public String promptAI(@RequestBody PromptRequest request) {
        System.out.println(request.getPrompt());
        return openaiservice.prompt(request.getPrompt());
    }
}