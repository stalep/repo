package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.TransformationLog;
import io.hyperfoil.tools.horreum.entity.alerting.TransformationLogDTO;

public class TransformationLogMapper {

    public static TransformationLogDTO from(TransformationLog tl) {
        return new TransformationLogDTO(tl.test.id, tl.run.id, tl.level, tl.message);
    }
}
