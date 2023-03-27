package io.hyperfoil.tools.horreum.entity.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import java.util.Objects;

public class ViewComponentDTO {
    @JsonProperty(required = true)
    public Integer id;
    public int headerOrder;
    public String headerName;
    public JsonNode labels;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String render;

    public ViewComponentDTO() {
    }

    public ViewComponentDTO(String headerName, String render, String... labels) {
        this.headerName = headerName;
        ArrayNode labelsNode = JsonNodeFactory.instance.arrayNode();
        for (String l : labels) {
            labelsNode.add(l);
        }

        this.labels = labelsNode;
        this.render = render;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ViewComponentDTO that = (ViewComponentDTO)o;
            return this.headerOrder == that.headerOrder &&
                    Objects.equals(this.id, that.id) &&
                    Objects.equals(this.headerName, that.headerName) &&
                    Objects.equals(this.labels, that.labels) &&
                    Objects.equals(this.render, that.render);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(id, headerOrder, headerName, labels, render);
    }
}
