package io.hyperfoil.tools.horreum.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class ExperimentComparisonDTO {

    public String model;
    public JsonNode config;

    @JsonProperty(value = "variableId", required = true )
    public Integer variableId;

    public String variableName;

    public ExperimentComparisonDTO() {
    }

}
