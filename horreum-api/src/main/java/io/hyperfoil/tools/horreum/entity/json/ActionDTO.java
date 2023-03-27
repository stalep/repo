package io.hyperfoil.tools.horreum.entity.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ActionDTO {
    @JsonProperty( required = true )
    public Integer id;
    public String event;
    public String type;
    public JsonNode config;
    @JsonIgnore
    public JsonNode secrets;
    public Integer testId;
    public boolean active = true;
    public boolean runAlways;

    public ActionDTO() {
    }

    public ActionDTO(Integer id, String event, String type, JsonNode config, JsonNode secrets,
                     Integer testId, boolean active, boolean runAlways) {
        this.id = id;
        this.event = event;
        this.type = type;
        this.config = config;
        this.secrets = secrets;
        this.testId = testId;
        this.active = active;
        this.runAlways = runAlways;
    }

    @JsonProperty("secrets")
    public void setSecrets(JsonNode secrets) {
        this.secrets = secrets;
    }

    @JsonProperty("secrets")
    public JsonNode getMaskedSecrets() {
        if (this.secrets != null && this.secrets.isObject()) {
            ObjectNode masked = JsonNodeFactory.instance.objectNode();
            this.secrets.fieldNames().forEachRemaining((name) -> {
                masked.put(name, "********");
            });
            return masked;
        } else {
            return JsonNodeFactory.instance.objectNode();
        }
    }
}
