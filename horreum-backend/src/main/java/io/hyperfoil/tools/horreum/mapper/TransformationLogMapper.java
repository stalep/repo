package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.TransformationLogDAO;
import io.hyperfoil.tools.horreum.api.alerting.TransformationLogDTO;

public class TransformationLogMapper {

    public static TransformationLogDTO from(TransformationLogDAO tl) {
        return new TransformationLogDTO(tl.test.id, tl.run.id, tl.level, tl.message);
    }
}
