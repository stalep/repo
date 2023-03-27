package io.hyperfoil.tools.horreum.entity.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class TransformerDTO {
    @JsonProperty(required = true)
    public Integer id;
    public String name;
    public String description;
    public String targetSchemaUri;
    public Collection<ExtractorDTO> extractors;
    public String function;
    @JsonProperty(value = "schemaId", required = true)
    public Integer schemaId;

    @JsonProperty(value = "schemaUri", required = true)
    public String schemaUri;

    @JsonProperty(value = "schemaName", required = true)
    public String schemaName;

    public String owner;

    public Access access = Access.PUBLIC;

    public TransformerDTO() {
    }

    public int compareTo(TransformerDTO o) {
        if (o != null) {
            return o == this ? 0 : Integer.compare(this.id, o.id);
        } else {
            throw new IllegalArgumentException("cannot compare a null reference");
        }
    }

    @Override
    public String toString() {
        return "TransformerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", targetSchemaUri='" + targetSchemaUri + '\'' +
                ", extractors=" + extractors +
                ", function='" + function + '\'' +
                ", schemaId=" + schemaId +
                ", schemaUri='" + schemaUri + '\'' +
                ", schemaName='" + schemaName + '\'' +
                ", owner='" + owner + '\'' +
                ", access=" + access +
                '}';
    }

    public void clearId() {
        id = null;
    }
}
