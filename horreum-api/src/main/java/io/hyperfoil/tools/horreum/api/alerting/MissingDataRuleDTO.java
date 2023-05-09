package io.hyperfoil.tools.horreum.api.alerting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.time.Instant;
import java.util.Objects;

public class MissingDataRuleDTO {
     @JsonProperty(
        required = true
    )
    public Integer id;
    public String name;
    //@JsonIgnore
    //public TestDTO test;
    public ArrayNode labels;
    public String condition;
    public long maxStaleness;
    public Instant lastNotification;

    @JsonProperty( value = "testId", required = true )
    public Integer testId;

    public MissingDataRuleDTO() {
    }


    /* stalp, ignoring it for now
    public void setTestId(int testId) {
        this.test = (Test)Test.getEntityManager().getReference(Test.class, testId);
    }
     */

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            MissingDataRuleDTO that = (MissingDataRuleDTO)o;
            return this.maxStaleness == that.maxStaleness && Objects.equals(this.labels, that.labels) && Objects.equals(this.condition, that.condition);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.labels, this.condition, this.maxStaleness});
    }
}
