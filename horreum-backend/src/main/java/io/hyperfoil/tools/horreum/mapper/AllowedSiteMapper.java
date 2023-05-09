package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.data.AllowedSiteDAO;
import io.hyperfoil.tools.horreum.api.data.AllowedSiteDTO;

public class AllowedSiteMapper {
    public static AllowedSiteDTO from(AllowedSiteDAO site) {
        AllowedSiteDTO dto = new AllowedSiteDTO();
        dto.id = site.id;
        dto.prefix = site.prefix;

        return dto;
    }
}
