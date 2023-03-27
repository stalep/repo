package io.hyperfoil.tools.horreum.entity.alerting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.hyperfoil.tools.horreum.entity.json.TestDTO;

import java.util.List;

public class WatchDTO {
    public Integer id;
    public List<String> users;
    public List<String> optout;
    public List<String> teams;
    @JsonProperty( value = "testId", required = true )
    public Integer testId;

    public WatchDTO() {
    }

    public String toString() {
        return "Watch{id=" + this.id + ", test=" + this.testId + ", users=" + this.users + ", optout=" + this.optout + ", teams=" + this.teams + '}';
    }
}
