package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.ExperimentComparison;
import io.hyperfoil.tools.horreum.entity.ExperimentComparisonDTO;
import io.hyperfoil.tools.horreum.entity.ExperimentProfile;
import io.hyperfoil.tools.horreum.entity.ExperimentProfileDTO;

import java.util.stream.Collectors;

public class ExperimentProfileMapper {

    public static ExperimentProfileDTO from(ExperimentProfile ep) {
        ExperimentProfileDTO dto = new ExperimentProfileDTO();
        dto.id = ep.id;
        dto.name = ep.name;
        dto.testId = ep.test.id;
        dto.baselineLabels = ep.baselineLabels;
        dto.extraLabels = ep.extraLabels;
        dto.selectorLabels = ep.selectorLabels;
        dto.baselineFilter = ep.baselineFilter;
        dto.selectorFilter = ep.selectorFilter;

        dto.comparisons = ep.comparisons.stream().map(ExperimentProfileMapper::fromExperimentComparison).collect(Collectors.toList());

        return dto;
    }

    public static ExperimentComparisonDTO fromExperimentComparison(ExperimentComparison ec) {
        ExperimentComparisonDTO dto = new ExperimentComparisonDTO();
        dto.variableId = ec.getVariableId();
        dto.variableName = ec.variable.name;
        dto.config = ec.config;
        dto.model = ec.model;

        return dto;
    }

    public static ExperimentProfile to(ExperimentProfileDTO dto) {
        ExperimentProfile ep = new ExperimentProfile();
        ep.id = dto.id;
        ep.name = dto.name;
        ep.baselineLabels = dto.baselineLabels;
        ep.extraLabels = dto.extraLabels;
        ep.selectorLabels = dto.selectorLabels;
        ep.baselineFilter = dto.baselineFilter;
        ep.selectorFilter = dto.selectorFilter;

        ep.comparisons = dto.comparisons.stream().map(ExperimentProfileMapper::toExperimentComparison).collect(Collectors.toList());

        return ep;
    }

    public static ExperimentComparison toExperimentComparison(ExperimentComparisonDTO dto) {
        ExperimentComparison ec = new ExperimentComparison();
        ec.config = dto.config;
        ec.model = dto.model;
        ec.setVariableId(dto.variableId);

        return ec;
    }
}
