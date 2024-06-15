package com.ltbc.swarmrestapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltbc.swarmrestapi.service.beesinfo.BeesInfoDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class SwarmRestApiService {

    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    @Value("${OPENAI_API_KEY}")
    private String apiKey;
    HttpClient client = HttpClient.newHttpClient();

    private static final Logger logger = LoggerFactory.getLogger(SwarmRestApiService.class);

    public BeesInfoDetails sendPromptToOpenAi(String requestPrompt) throws IOException, InterruptedException {
        if (apiKey == null) {
            return null; // TODO: fix up these to return an empty BeesInfoDetails obj
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(OPENAI_URL))
                .header("Content-Type", "application/json") // Essential for JSON payloads
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(getJsonReqStr(requestPrompt)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        String responseBody = response.body();

        if (statusCode == 200) {
            // TODO: maybe move stuff here instead of below to only process when successful
            logger.info("Success: " + responseBody);
        } else {
            // TODO: Handle errors (e.g., rate limiting, invalid requests, etc)
            logger.error("Error: " + statusCode + " - " + responseBody);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);

        // Navigate to the content field
        String contentStr = rootNode.path("choices").get(0).path("message").path("content").asText();

        return objectMapper.readValue(contentStr, BeesInfoDetails.class);
    }

    private static String getJsonReqStr(String requestPrompt) {
        // We are including only some of the parameters to the json request
        String responseJsonStructure = "{hivePopulation: {isHealthy: Boolean, desc: String},food: {isFoodPresent: Boolean, desc: String},queenInfo: {isPresent: Boolean, desc: String}, beeStressors: {status: Boolean, desc: String},colonyTemper: { isAggressive: Boolean, desc: String} }";
        return String.format("""
                            {
                              "messages": [
                                {
                                  "role": "system",
                                  "content": "You are a beekeeper assistant. Please distill out important points from the user report. Return as a response a single JSON object which should look like -> %s. and the desc field should be concise and not too long"
                                },
                                {
                                  "role": "user",
                                  "content": "%s"
                                }
                              ],
                              "model": "gpt-3.5-turbo-0125",
                              "max_tokens": 600
                            }
                """, responseJsonStructure, requestPrompt);
    }
}
