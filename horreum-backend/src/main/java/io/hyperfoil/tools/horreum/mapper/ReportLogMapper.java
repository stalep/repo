package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.report.ReportLog;
import io.hyperfoil.tools.horreum.api.report.ReportLogDTO;

public class ReportLogMapper {
    public static ReportLogDTO from(ReportLog rl) {
        return new ReportLogDTO(rl.getReportId(), rl.level, rl.message);
    }
}
