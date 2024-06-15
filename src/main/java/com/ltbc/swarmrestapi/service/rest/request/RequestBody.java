package com.ltbc.swarmrestapi.service.rest.request;

import java.util.ArrayList;

public record RequestBody(String model, ArrayList<OAIMessage> messages, int maxTokens) {
}
