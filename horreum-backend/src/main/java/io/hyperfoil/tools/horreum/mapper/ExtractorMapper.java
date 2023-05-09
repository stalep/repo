package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.data.Extractor;
import io.hyperfoil.tools.horreum.api.data.ExtractorDTO;

public class ExtractorMapper {

    public static ExtractorDTO from(Extractor e) {
        ExtractorDTO dto = new ExtractorDTO();
        dto.name = e.name;
        dto.array = e.array;
        dto.jsonpath = e.jsonpath;

        return dto;
    }

    public static Extractor to(ExtractorDTO dto) {
        Extractor e = new Extractor();
        e.name = dto.name;
        e.array = dto.array;
        e.jsonpath = dto.jsonpath;

        return e;
    }
}
