package com.littletech.swarmrestapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.littletech.swarmrestapi.service.OpenAIService;

@SpringBootTest
@AutoConfigureMockMvc
public class OpenAIControllertest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OpenAIService openAIService;

    @Test
    public void testPromptAI() throws Exception {
        String requestBody = "{\"prompt\":\"test\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/prompt")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}