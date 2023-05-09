package io.hyperfoil.tools.horreum.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Collection;

public class SchemaDTO {

    public static final int TYPE_1ST_LEVEL = 0;
    public static final int TYPE_2ND_LEVEL = 1;
    public static final int TYPE_ARRAY_ELEMENT = 2;
    @JsonProperty(required = true)
    public Integer id;
    public String uri;
    public String name;
    public String description;
    public JsonNode schema;
    public String owner;
    public Access access;
    public String token;

    public SchemaDTO() {
        access = Access.PUBLIC;
    }

    public static class ValidationEvent {
        public int id;
        public Collection<ValidationErrorDTO> errors;

        public ValidationEvent(int id, Collection<ValidationErrorDTO> errors) {
            this.id = id;
            this.errors = errors;
        }
    }
}
