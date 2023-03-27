package io.hyperfoil.tools.horreum.entity.json;

public class ExtractorDTO {
    public String name;
    public String jsonpath;
    public boolean array;

    public ExtractorDTO() {
    }

    public ExtractorDTO(String name, String jsonpath, boolean array) {
        this.name = name;
        this.jsonpath = jsonpath;
        this.array = array;
    }
}
