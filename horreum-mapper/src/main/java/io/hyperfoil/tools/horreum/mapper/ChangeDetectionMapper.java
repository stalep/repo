package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.ChangeDetection;
import io.hyperfoil.tools.horreum.entity.alerting.ChangeDetectionDTO;

public class ChangeDetectionMapper {

    public static ChangeDetectionDTO from(ChangeDetection cd) {
        ChangeDetectionDTO dto = new ChangeDetectionDTO();
        dto.id = cd.id;
        dto.config = cd.config;
        dto.model = cd.model;

        return dto;
    }
}
