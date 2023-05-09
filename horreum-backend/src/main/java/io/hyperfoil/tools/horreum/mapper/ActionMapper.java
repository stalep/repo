package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.data.Action;
import io.hyperfoil.tools.horreum.api.data.ActionDTO;

public class ActionMapper {

    public static ActionDTO from(Action action) {
        return new ActionDTO(action.id, action.event, action.type,
                action.config, action.secrets, action.testId, action.active, action.runAlways);

    }

    public static Action to(ActionDTO action) {
        return new Action(action.id, action.event, action.type,
                action.config, action.secrets, action.testId, action.active, action.runAlways);
    }
}
