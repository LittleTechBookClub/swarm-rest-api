package com.littletech.swarmrestapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIService {

    @SuppressWarnings("unused")
    private static final String examplePrompt = "today on march 6 2042 my bees show sign of infection. There are holes in the cell cap. Food is really scarse on the first and last frames. I hardly see any broods within the hive. The queen has left the hive";
    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${OPENAI_API_KEY}")
    private String apiKey;
    private final RestTemplate restTemplate = new RestTemplate();

    public String prompt(String string) {
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
                string);

        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(OPENAI_URL, request, String.class);
        return response.getBody();

    }

}
