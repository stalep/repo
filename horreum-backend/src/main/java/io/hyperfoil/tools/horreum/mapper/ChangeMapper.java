package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.ChangeDAO;
import io.hyperfoil.tools.horreum.api.alerting.ChangeDTO;

public class ChangeMapper {

    public static ChangeDTO from(ChangeDAO c) {
        ChangeDTO dto = new ChangeDTO();
        dto.id = c.id;
        dto.variable = VariableMapper.from(c.variable);
        dto.dataset = DataSetMapper.from(c.dataset);
        dto.timestamp = c.timestamp;
        dto.confirmed = c.confirmed;
        dto.description = c.description;

        return dto;
    }

}
