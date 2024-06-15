package com.ltbc.swarmrestapi.service.beesinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record QueenInfo(boolean isPresent,  String desc) {
}
