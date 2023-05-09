package io.hyperfoil.tools.horreum.api.alerting;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hyperfoil.tools.horreum.api.data.PersistentLogDTO;

public class TransformationLogDTO extends PersistentLogDTO {
    @JsonProperty("testId")
    private Integer testId;

    @JsonProperty("runId")
    private Integer runId;

    public TransformationLogDTO() {
        super(0, (String)null);
    }

    public TransformationLogDTO(Integer testId, Integer runId, int level, String message) {
        super(level, message);
        this.testId = testId;
        this.runId = runId;
    }
}
