package com.ltbc.swarmrestapi.service.rest;

import com.ltbc.swarmrestapi.service.SwarmRestApiService;
import com.ltbc.swarmrestapi.service.beesinfo.*;
import com.ltbc.swarmrestapi.service.rest.request.PromptRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OpenAIApiControllerTest {

    @Mock
    private SwarmRestApiService swarmRestApiService;

    @InjectMocks
    private OpenAIApiController openAIApiController;

    @Test
    public void testProcessInspectionSuccess() throws IOException, InterruptedException {
        String prompt = "{\"prompt\":\"test\"}";
        PromptRequest promptRequest = new PromptRequest(prompt);
        BeesInfoDetails mockBeesInfo = new BeesInfoDetails(
                new HivePopulation(true, "it is healthy"),
                new Food(true, "has food"),
                new QueenInfo(true, "queen is present"),
                new BeeStressors(false, "no stressors"),
                new ColonyTemper(true, "colony seems calm")); // Sample BeesInfoDetails
        ;

        when(swarmRestApiService.sendPromptToOpenAi(prompt)).thenReturn(mockBeesInfo);

        BeesInfoDetails result = openAIApiController.processInspection(promptRequest);

        assertEquals(mockBeesInfo, result); // Directly compare the returned BeesInfoDetails
        verify(swarmRestApiService, times(1)).sendPromptToOpenAi(prompt);
    }

    @Test
    public void testProcessInspectionError() throws IOException, InterruptedException {
        String prompt = "some prompt that will throw an error? too long? etc";
        PromptRequest promptRequest = new PromptRequest(prompt);

        when(swarmRestApiService.sendPromptToOpenAi(prompt)).thenThrow(new RuntimeException("some simulated error error"));

        BeesInfoDetails result = openAIApiController.processInspection(promptRequest);

        assertNull(result);
        verify(swarmRestApiService, times(1)).sendPromptToOpenAi(prompt);
    }
}
