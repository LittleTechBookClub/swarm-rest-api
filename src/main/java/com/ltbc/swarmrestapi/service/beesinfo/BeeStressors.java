package com.ltbc.swarmrestapi.service.beesinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BeeStressors(boolean status, String desc) {
}
