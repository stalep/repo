package io.hyperfoil.tools.horreum.api.alerting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class ChangeDetectionDTO {
    @JsonProperty(
            required = true
    )
    public Integer id;
    public String model;
    public JsonNode config;
    //@JsonIgnore
    //public VariableDTO variable;

    public ChangeDetectionDTO() {
    }

    public ChangeDetectionDTO(Integer id, String model, JsonNode config) {
        this.id = id;
        this.model = model;
        this.config = config;
    }

    public String toString() {
        return "ChangeDetectionDTO{id=" + this.id + ", model='" + this.model + '\'' + ", config=" + this.config + '}';
    }
}
