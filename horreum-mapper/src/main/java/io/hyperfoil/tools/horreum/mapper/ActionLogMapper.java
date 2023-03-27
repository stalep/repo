package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.ActionLog;
import io.hyperfoil.tools.horreum.entity.ActionLogDTO;

public class ActionLogMapper {

    public static ActionLogDTO from(ActionLog al) {
        return new ActionLogDTO(al.level, al.testId, al.event, al.type, al.message);
    }
}
