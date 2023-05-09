package io.hyperfoil.tools.horreum.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ViewDTO {
    @JsonProperty(required = true)
    public Integer id;
    public String name;
    public Integer testId;
    public List<ViewComponentDTO> components;

    public ViewDTO() {
    }

    public ViewDTO(String name, Integer testId) {
        this.name = name;
        this.testId = testId;
    }

    @Override
    public String toString() {
        return "ViewDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", testId=" + testId +
                ", components=" + components +
                '}';
    }

    public void clearId() {
        id = null;
        if(components != null)
            components.stream().forEach( c -> {c.id = null;});
    }
}
