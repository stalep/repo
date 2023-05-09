package io.hyperfoil.tools.horreum.api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;
import java.util.Collection;

public class Run {
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
    public Collection<DataSet> datasets;
    public Collection<ValidationError> validationErrors;
    public String owner;
    public Access access;

    public Run() {
    }
}
