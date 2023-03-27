package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.ChangeDetection;
import io.hyperfoil.tools.horreum.entity.alerting.ChangeDetectionDTO;
import io.hyperfoil.tools.horreum.entity.alerting.Variable;
import io.hyperfoil.tools.horreum.entity.alerting.VariableDTO;

import java.util.stream.Collectors;

public class VariableMapper {

    public static VariableDTO from(Variable variable) {
        return new VariableDTO(variable.id, variable.testId, variable.name, variable.group,
                variable.order, variable.labels,
                variable.changeDetection.stream().map(VariableMapper::fromChangeDetection).collect(Collectors.toSet())
        );
    }

    public static Variable to(VariableDTO dto) {
        Variable v = new Variable();
        v.id = dto.id;
        v.testId = dto.testId;
        v.name = dto.name;
        v.group = dto.group;
        v.order = dto.order;
        v.labels = dto.labels;
        if(dto.changeDetection != null)
            v.changeDetection = dto.changeDetection.stream().map(VariableMapper::toChangeDetection).collect(Collectors.toSet());

        return v;
    }

    public static ChangeDetectionDTO fromChangeDetection(ChangeDetection cd) {
        return new ChangeDetectionDTO(cd.id, cd.model, cd.config);
    }

    public static ChangeDetection toChangeDetection(ChangeDetectionDTO dto) {
       ChangeDetection cd = new ChangeDetection();
       cd.id = dto.id;
       cd.model = dto.model;
       cd.config = dto.config;
       return cd;
    }
}

