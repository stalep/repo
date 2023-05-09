package io.hyperfoil.tools.horreum.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class ValidationErrorDTO {
    public int schemaId;
    public JsonNode error;

    public ValidationErrorDTO() {
    }

    @JsonProperty(
        value = "schemaId",
        required = true
    )
    public void setSchema(int id) {
        this.schemaId = id;
    }

    @JsonProperty(
        value = "schemaId",
        required = true
    )
    public Integer getSchemaId() {
        return schemaId;
    }
}
