package com.littletech.swarmrestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/")
public class OpenAIController {

    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";
    private static final String prompt = "today on march 6 2042 my bees show sign of infection. There are holes in the cell cap. Food is really scarse on the first and last frames. I hardly see any broods within the hive. The queen has left the hive";

    @Value("${OPENAI_API_KEY}")
    private String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/prompt")
    public String example() {
        if (apiKey == null) {
            return "API Key not found";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // We are including only some of the parameters to the json request
        String requestJson = String.format(
                "{\n" +
                        "    \"messages\": [\n" +
                        "      {\n" +
                        "        \"role\": \"system\",\n" +
                        "        \"content\": \"You are a beekeeper assistant. Please distill out important points from the user report.\"\n"
                        +
                        "      },\n" +
                        "      { \"role\": \"user\", \"content\": \"%s\" }\n" +
                        "    ],\n" +
                        "    \"model\": \"gpt-3.5-turbo-0125\",\n" +
                        "    \"max_tokens\": 100\n" +
                        "}",
                prompt);

        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(OPENAI_URL, request, String.class);
        return response.getBody();

    }
}