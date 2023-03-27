package io.hyperfoil.tools.horreum.entity.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Collection;

public class TestDTO {
    @JsonProperty(required = true)
    public Integer id;
    public String name;
    public String folder;
    public String description;
    public String owner;
    public Access access;
    public Collection<TestTokenDTO> tokens;
    @Schema(implementation = String.class)
    public JsonNode timelineLabels;
    public String timelineFunction;
    @Schema(implementation = String.class)
    public JsonNode fingerprintLabels;
    public String fingerprintFilter;
    public Collection<ViewDTO> views;
    public String compareUrl;
    public Collection<TransformerDTO> transformers;
    public Boolean notificationsEnabled;

    public TestDTO() {
        this.access = Access.PUBLIC;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", folder='" + folder + '\'' +
                ", description='" + description + '\'' +
                ", owner='" + owner + '\'' +
                ", access=" + access +
                ", tokens=" + tokens +
                ", timelineLabels=" + timelineLabels +
                ", timelineFunction='" + timelineFunction + '\'' +
                ", fingerprintLabels=" + fingerprintLabels +
                ", fingerprintFilter='" + fingerprintFilter + '\'' +
                ", views=" + views +
                ", compareUrl='" + compareUrl + '\'' +
                ", transformers=" + transformers +
                ", notificationsEnabled=" + notificationsEnabled +
                '}';
    }

    public void clearIds() {
        id = null;
        if(tokens != null)
            tokens.stream().forEach( t -> t.clearId());
        if(views != null)
            views.stream().forEach( v -> v.clearId());
        //if(transformers != null)
        //    transformers.stream().forEach( t-> t.clearId());
    }
}
