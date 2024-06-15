package com.ltbc.swarmrestapi.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class OpenAIApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private final SwarmRestApiService swarmRestApiService;

    public OpenAIApiControllerTest(SwarmRestApiService swarmRestApiService) {
        this.swarmRestApiService = swarmRestApiService;
    }

    @BeforeEach
    public void setup() throws IOException, InterruptedException {
        when(swarmRestApiService.sendPromptToOpenAi("test")).thenReturn(null); // TODO: remove null and fix test
    }

    @Test
    public void testPromptAI() throws Exception {
        String requestBody = "{\"prompt\":\"test\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/process-inspection")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}