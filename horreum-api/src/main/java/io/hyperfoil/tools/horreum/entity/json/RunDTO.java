package io.hyperfoil.tools.horreum.entity.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import io.hyperfoil.tools.horreum.entity.ValidationErrorDTO;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;
import java.util.Collection;

public class RunDTO {
    @JsonProperty(required = true)
    public Integer id;
    @Schema(type = SchemaType.NUMBER)
    public Instant start;
    @Schema(type = SchemaType.NUMBER)
    public Instant stop;
    public String description;
    public Integer testid;
    public JsonNode data;
    public JsonNode metadata;
    public boolean trashed;
    @JsonIgnore
    public Collection<DataSetDTO> datasets;
    public Collection<ValidationErrorDTO> validationErrors;
    public String owner;
    public Access access;

    public RunDTO() {
    }
}
