package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.report.ReportComment;
import io.hyperfoil.tools.horreum.entity.report.ReportCommentDTO;

public class ReportCommentMapper {
    public static ReportCommentDTO from(ReportComment rc) {
        ReportCommentDTO dto = new ReportCommentDTO();
        dto.id = rc.id;
        dto.comment = rc.comment;
        dto.category = rc.category;
        dto.componentId = rc.componentId;
        dto.level = rc.level;

        return dto;
    }

    public static ReportComment to(ReportCommentDTO dto) {
        ReportComment rc = new ReportComment();
        rc.id = dto.id;
        rc.comment = dto.comment;
        rc.category = dto.category;
        rc.componentId = dto.componentId;
        rc.level = dto.level;

        return rc;
    }
}
