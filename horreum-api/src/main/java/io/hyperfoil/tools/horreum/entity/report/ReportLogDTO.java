package io.hyperfoil.tools.horreum.entity.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hyperfoil.tools.horreum.entity.PersistentLogDTO;

public class ReportLogDTO extends PersistentLogDTO {
    private int reportId;

    public ReportLogDTO() {
        super(0, (String)null);
    }

    public ReportLogDTO(int reportId, int level, String message) {
        super(level, message);
        this.reportId = reportId;
    }

    @JsonProperty(required = true, value = "reportId")
    public int getReportId() {
        return this.reportId;
    }

}
