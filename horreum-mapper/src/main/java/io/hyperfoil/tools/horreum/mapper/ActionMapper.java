package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.json.Action;
import io.hyperfoil.tools.horreum.entity.json.ActionDTO;

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
