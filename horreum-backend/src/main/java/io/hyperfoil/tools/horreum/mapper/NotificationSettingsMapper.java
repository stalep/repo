package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.NotificationSettingsDAO;
import io.hyperfoil.tools.horreum.api.alerting.NotificationSettingsDTO;

public class NotificationSettingsMapper {
    public static NotificationSettingsDTO from(NotificationSettingsDAO ns) {
        NotificationSettingsDTO dto = new NotificationSettingsDTO();
        dto.id = ns.id;
        dto.name = ns.name;
        dto.isTeam = ns.isTeam;
        dto.method = ns.method;
        dto.data = ns.data;
        dto.disabled = ns.disabled;

        return dto;
    }
}
