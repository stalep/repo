package io.hyperfoil.tools.horreum.api.alerting;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Watch {
    public Integer id;
    public List<String> users;
    public List<String> optout;
    public List<String> teams;
    @JsonProperty( value = "testId", required = true )
    public Integer testId;

    public Watch() {
    }

    public String toString() {
        return "Watch{id=" + this.id + ", test=" + this.testId + ", users=" + this.users + ", optout=" + this.optout + ", teams=" + this.teams + '}';
    }
}
