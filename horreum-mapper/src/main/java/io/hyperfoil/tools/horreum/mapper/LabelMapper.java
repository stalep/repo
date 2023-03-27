package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.json.*;

import java.util.stream.Collectors;

public class LabelMapper {
    public static LabelDTO from(Label l) {
        LabelDTO dto = new LabelDTO();
        dto.id = l.id;
        dto.name = l.name;
        dto.function = l.function;
        dto.filtering = l.filtering;
        dto.metrics = l.metrics;
        dto.owner = l.owner;
        dto.access = l.access;
        dto.schemaId = l.schema.id;
        dto.extractors = l.extractors.stream().map(ExtractorMapper::from).collect(Collectors.toList());

        return dto;
    }

    public static Label to(LabelDTO dto) {
        Label l = new Label();
        l.id = dto.id;
        l.name = dto.name;
        l.function = dto.function;
        l.filtering = dto.filtering;
        l.metrics = dto.metrics;
        l.owner = dto.owner;
        l.access = dto.access;
        if(dto.schemaId > 0)
            l.schema = Schema.getEntityManager().find(Schema.class, dto.schemaId);
        l.extractors = dto.extractors.stream().map(ExtractorMapper::to).collect(Collectors.toList());

        return l;
    }

    public static LabelDTO.Value fromValue(Label.Value v) {
        LabelDTO.Value dto = new LabelDTO.Value();
        dto.labelId = v.labelId;
        dto.value = v.value;
        dto.datasetId = v.datasetId;

        return dto;
    }
}
