package com.littletech.swarmrestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
public class ExampleOpenAiController {

    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";
    private static final String prompt = "Help me be a beekeeper";

    @Value("${OPENAI_API_KEY:default_value}")
    private String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/example")

    public String example() {
        if (apiKey == null) {
            return "API Key not found";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // We are including only some of the parameters to the json request
        String requestJson = "{\"prompt\":\"" + prompt + "\",\"n\"}";

        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(OPENAI_URL, request, String.class);
        return response.getBody();

    }
}