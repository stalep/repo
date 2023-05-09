package io.hyperfoil.tools.horreum.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class ExperimentComparison {

    public String model;
    public JsonNode config;

    @JsonProperty(value = "variableId", required = true )
    public Integer variableId;

    public String variableName;

    public ExperimentComparison() {
    }

}
