package io.hyperfoil.tools.horreum.api.alerting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.hyperfoil.tools.horreum.api.data.DataSetDTO;

import java.time.Instant;

public class ChangeDTO {
    public static final String EVENT_NEW = "change/new";
    @JsonProperty(
            required = true
    )
    public int id;
    public VariableDTO variable;
    @JsonIgnore
    public DataSetDTO dataset;
    public Instant timestamp;
    public boolean confirmed;
    public String description;

    public ChangeDTO() {
    }

    @JsonProperty("dataset")
    public DataSetDTO.Info getDatasetId() {
        return this.dataset != null ? this.dataset.getInfo() : null;
    }

    public String toString() {
        return "Change{id=" + this.id + ", variable=" + this.variable.id + ", dataset=" + this.dataset.id + " (" + this.dataset.runId + "/" + this.dataset.ordinal + "), timestamp=" + this.timestamp + ", confirmed=" + this.confirmed + ", description='" + this.description + '\'' + '}';
    }


    public static class Event {
        public ChangeDTO change;
        public String testName;
        public DataSetDTO.Info dataset;
        public boolean notify;

        public Event() {
        }

        public Event(ChangeDTO change, String testName, DataSetDTO.Info dataset, boolean notify) {
            this.change = change;
            this.testName = testName;
            this.dataset = dataset;
            this.notify = notify;
        }

        public String toString() {
            return "ChangeDTO.Event{change=" + this.change + ", dataset=" + this.dataset + ", notify=" + this.notify + '}';
        }
    }
}
