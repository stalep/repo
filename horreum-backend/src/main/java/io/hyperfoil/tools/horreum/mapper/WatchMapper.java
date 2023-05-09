package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.WatchDAO;
import io.hyperfoil.tools.horreum.api.alerting.WatchDTO;
import io.hyperfoil.tools.horreum.entity.data.TestDAO;

public class WatchMapper {
    public static WatchDTO from(WatchDAO w) {
        WatchDTO dto = new WatchDTO();
        dto.id = w.id;
        dto.optout = w.optout;
        dto.teams = w.teams;
        dto.users = w.users;
        dto.testId = w.test.id;

        return dto;
    }

    public static WatchDAO to(WatchDTO dto) {
        WatchDAO w = new WatchDAO();
        w.id = dto.id;
        w.optout = dto.optout;
        w.teams = dto.teams;
        w.users = dto.users;
        if (dto.testId != null)
            w.test = TestDAO.getEntityManager().getReference(TestDAO.class, dto.testId);

        return w;
    }
}
