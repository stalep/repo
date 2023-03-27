package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.json.Schema;
import io.hyperfoil.tools.horreum.entity.json.SchemaDTO;

public class SchemaMapper {
    public static SchemaDTO from(Schema s) {
        SchemaDTO dto = new SchemaDTO();
        dto.id = s.id;
        dto.name = s.name;
        dto.schema = s.schema;
        dto.description = s.description;
        dto.uri = s.uri;
        dto.owner = s.owner;
        dto.access = s.access;
        dto.token = s.token;

        return dto;
    }

    public static Schema to(SchemaDTO dto) {
        Schema s = new Schema();
        s.id = dto.id;
        s.name = dto.name;
        s.schema = dto.schema;
        s.description = dto.description;
        s.uri = dto.uri;
        s.owner = dto.owner;
        s.access = dto.access;
        s.token = dto.token;

        return s;
    }
}
