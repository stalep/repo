package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.Watch;
import io.hyperfoil.tools.horreum.api.alerting.WatchDTO;
import io.hyperfoil.tools.horreum.entity.data.Test;

public class WatchMapper {
    public static WatchDTO from(Watch w) {
        WatchDTO dto = new WatchDTO();
        dto.id = w.id;
        dto.optout = w.optout;
        dto.teams = w.teams;
        dto.users = w.users;
        dto.testId = w.test.id;

        return dto;
    }

    public static Watch to(WatchDTO dto) {
        Watch w = new Watch();
        w.id = dto.id;
        w.optout = dto.optout;
        w.teams = dto.teams;
        w.users = dto.users;
        if (dto.testId != null)
            w.test = Test.getEntityManager().getReference(Test.class, dto.testId);

        return w;
    }
}
