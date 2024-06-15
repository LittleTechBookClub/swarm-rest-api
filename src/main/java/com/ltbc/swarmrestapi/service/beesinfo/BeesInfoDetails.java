package com.ltbc.swarmrestapi.service.beesinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record BeesInfoDetails(HivePopulation hivePopulation, Food food, QueenInfo queenInfo, BeeStressors beeStressors, ColonyTemper colonyTemper) {
}
