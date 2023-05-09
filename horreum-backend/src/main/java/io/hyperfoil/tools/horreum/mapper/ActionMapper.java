package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.data.ActionDAO;
import io.hyperfoil.tools.horreum.api.data.ActionDTO;

public class ActionMapper {

    public static ActionDTO from(ActionDAO action) {
        return new ActionDTO(action.id, action.event, action.type,
                action.config, action.secrets, action.testId, action.active, action.runAlways);

    }

    public static ActionDAO to(ActionDTO action) {
        return new ActionDAO(action.id, action.event, action.type,
                action.config, action.secrets, action.testId, action.active, action.runAlways);
    }
}
