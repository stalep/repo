package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.json.AllowedSite;
import io.hyperfoil.tools.horreum.entity.json.AllowedSiteDTO;

public class AllowedSiteMapper {
    public static AllowedSiteDTO from(AllowedSite site) {
        AllowedSiteDTO dto = new AllowedSiteDTO();
        dto.id = site.id;
        dto.prefix = site.prefix;

        return dto;
    }
}
