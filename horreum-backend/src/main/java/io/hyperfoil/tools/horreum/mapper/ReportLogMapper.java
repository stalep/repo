package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.report.ReportLogDAO;
import io.hyperfoil.tools.horreum.api.report.ReportLogDTO;

public class ReportLogMapper {
    public static ReportLogDTO from(ReportLogDAO rl) {
        return new ReportLogDTO(rl.getReportId(), rl.level, rl.message);
    }
}
