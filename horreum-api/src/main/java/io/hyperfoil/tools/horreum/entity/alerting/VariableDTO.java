package io.hyperfoil.tools.horreum.entity.alerting;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Set;

public class VariableDTO {
    @JsonProperty(
        required = true
    )
    public Integer id;
    public int testId;
    public String name;
    public String group;
    public int order;
    public JsonNode labels;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String calculation;
    @Schema(
        required = true,
        implementation = ChangeDetectionDTO.class
    )
    public Set<ChangeDetectionDTO> changeDetection;

    public VariableDTO() {
    }

    public VariableDTO(Integer id, int testId, String name, String group, int order, JsonNode labels,
                       Set<ChangeDetectionDTO> changeDetection) {
        this.id = id;
        this.testId = testId;
        this.name = name;
        this.group = group;
        this.order = order;
        this.labels = labels;
        this.changeDetection = changeDetection;
    }

    public String toString() {
        return "VariableDTO{id=" + this.id + ", testId=" + this.testId + ", name='" + this.name + '\'' + ", group='" + this.group + '\'' + ", order=" + this.order + ", labels=" + this.labels + ", calculation='" + this.calculation + '\'' + ", changeDetection=" + this.changeDetection + '}';
    }

}
