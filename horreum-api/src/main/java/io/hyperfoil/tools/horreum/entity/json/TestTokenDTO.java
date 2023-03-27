package io.hyperfoil.tools.horreum.entity.json;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.function.Function;

public class TestTokenDTO {
    public static final int READ = 1;
    public static final int MODIFY = 2;
    public static final int UPLOAD = 4;
    public Integer id;
    public Integer testId;
    private String value;
    public int permissions;
    public String description;

    public TestTokenDTO() {
    }

    @JsonSetter("value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonGetter("value")
    public String getValue() {
        return value;
    }

    public boolean hasRead() {
        return (this.permissions & 1) != 0;
    }

    public boolean hasModify() {
        return (this.permissions & 2) != 0;
    }

    public boolean hasUpload() {
        return (this.permissions & 4) != 0;
    }

    public boolean valueEquals(String value) {
        return this.value.equals(value);
    }

    public String getEncryptedValue(Function<String, String> encrypt) {
        return (String)encrypt.apply(this.value);
    }

    public void decryptValue(Function<String, String> decrypt) {
        this.value = (String)decrypt.apply(this.value);
    }

    @Override
    public String toString() {
        return "TestTokenDTO{" +
                "id=" + id +
                ", testId=" + testId +
                ", value='" + value + '\'' +
                ", permissions=" + permissions +
                ", description='" + description + '\'' +
                '}';
    }

    public void clearId() {
        id = null;
        testId = null;
    }
}
