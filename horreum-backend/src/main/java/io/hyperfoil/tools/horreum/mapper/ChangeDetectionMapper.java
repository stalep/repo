package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.ChangeDetectionDAO;
import io.hyperfoil.tools.horreum.api.alerting.ChangeDetectionDTO;

public class ChangeDetectionMapper {

    public static ChangeDetectionDTO from(ChangeDetectionDAO cd) {
        ChangeDetectionDTO dto = new ChangeDetectionDTO();
        dto.id = cd.id;
        dto.config = cd.config;
        dto.model = cd.model;

        return dto;
    }
}
