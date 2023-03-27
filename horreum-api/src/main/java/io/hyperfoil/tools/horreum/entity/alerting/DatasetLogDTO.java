package io.hyperfoil.tools.horreum.entity.alerting;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hyperfoil.tools.horreum.entity.PersistentLogDTO;

public class DatasetLogDTO extends PersistentLogDTO {
    public String source;

    @JsonProperty( value = "testId", required = true )
    private Integer testId;

    @JsonProperty( value = "runId", required = true )
    private Integer runId;

    @JsonProperty( value = "datasetId", required = true )
    private Integer datasetId;

    @JsonProperty( value = "datasetOrdinal", required = true )
    private Integer datasetOrdinal;

    public DatasetLogDTO() {
        super(0, (String)null);
    }

    public DatasetLogDTO(Integer testId, Integer datasetId, Integer datasetOrdinal, Integer runId,
                         int level, String source, String message) {
        super(level, message);
        this.testId = testId;
        this.datasetId = datasetId;
        this.datasetOrdinal = datasetOrdinal;
        this.runId = runId;
        this.source = source;
    }
}
