package com.ltbc.swarmrestapi.service.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ltbc.swarmrestapi.service.beesinfo.BeesInfoDetails;
import com.ltbc.swarmrestapi.service.rest.request.PromptRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.ltbc.swarmrestapi.service.SwarmRestApiService;

@RestController
@RequestMapping("/")
public class OpenAIApiController {

    private static final Logger logger = LoggerFactory.getLogger(OpenAIApiController.class);
    private final SwarmRestApiService swarmRestApiService;

    public OpenAIApiController(SwarmRestApiService swarmRestApiService) {
        this.swarmRestApiService = swarmRestApiService;
    }

    @PostMapping("/process-inspection")
    public BeesInfoDetails processInspection(@RequestBody PromptRequest promptRequest) {
        try {
            logger.info("Making the request to OpenAPI"); // TODO: find a better place for this
            return swarmRestApiService.sendPromptToOpenAi(promptRequest.prompt());
        } catch (Exception ex) {
            logger.info("Some error occurred: " + ex);
            return null; // TODO: return some error before
        }
    }
}