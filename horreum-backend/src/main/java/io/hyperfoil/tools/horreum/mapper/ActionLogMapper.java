package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.ActionLogDAO;
import io.hyperfoil.tools.horreum.api.data.ActionLogDTO;

public class ActionLogMapper {

    public static ActionLogDTO from(ActionLogDAO al) {
        return new ActionLogDTO(al.level, al.testId, al.event, al.type, al.message);
    }
}
