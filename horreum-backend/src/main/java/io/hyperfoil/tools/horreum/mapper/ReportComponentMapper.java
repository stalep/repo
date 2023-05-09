package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.report.ReportComponentDAO;
import io.hyperfoil.tools.horreum.api.report.ReportComponentDTO;

public class ReportComponentMapper {
    public static ReportComponentDTO from(ReportComponentDAO rc) {
        ReportComponentDTO dto = new ReportComponentDTO();
        dto.id = rc.id;
        dto.name = rc.name;
        dto.function = rc.function;
        dto.unit = rc.unit;
        dto.labels = rc.labels;
        dto.order = rc.order;
        dto.reportId = rc.report.id;

        return dto;
    }

    public static ReportComponentDAO to(ReportComponentDTO dto) {
        ReportComponentDAO rc = new ReportComponentDAO();
        rc.id = dto.id;
        rc.name = dto.name;
        rc.function = dto.function;
        rc.unit = dto.unit;
        rc.labels = dto.labels;
        rc.order = dto.order;
        //rc.report.id = dto.reportId; ignoring this for now as it's lazy loaded

        return rc;
    }
}
