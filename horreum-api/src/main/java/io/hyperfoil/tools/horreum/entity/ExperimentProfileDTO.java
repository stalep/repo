package io.hyperfoil.tools.horreum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import io.hyperfoil.tools.horreum.entity.json.TestDTO;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Collection;

public class ExperimentProfileDTO {
    @JsonProperty(required = true )
    public Integer id;
    public String name;

    public Integer testId;
    @Schema(implementation = String.class, required = true )
    public JsonNode selectorLabels;
    public String selectorFilter;
    @Schema(implementation = String.class, required = true )
    public JsonNode baselineLabels;
    public String baselineFilter;
    @Schema(required = true )
    public Collection<ExperimentComparisonDTO> comparisons;
    @Schema(implementation = String.class )
    public JsonNode extraLabels;

    public ExperimentProfileDTO() {
    }
}
