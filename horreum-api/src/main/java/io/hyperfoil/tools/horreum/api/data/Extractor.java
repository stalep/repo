package io.hyperfoil.tools.horreum.api.data;

public class Extractor {
    public String name;
    public String jsonpath;
    public boolean array;

    public Extractor() {
    }

    public Extractor(String name, String jsonpath, boolean array) {
        this.name = name;
        this.jsonpath = jsonpath;
        this.array = array;
    }
}
