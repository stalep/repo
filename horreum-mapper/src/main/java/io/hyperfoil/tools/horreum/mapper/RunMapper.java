package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.ValidationError;
import io.hyperfoil.tools.horreum.entity.ValidationErrorDTO;
import io.hyperfoil.tools.horreum.entity.json.Run;
import io.hyperfoil.tools.horreum.entity.json.RunDTO;

import java.util.stream.Collectors;

public class RunMapper {

    public static RunDTO from(Run run) {
        RunDTO dto = new RunDTO();
        dto.id = run.id;
        dto.start = run.start;
        dto.stop = run.stop;
        dto.description = run.description;
        dto.testid = run.testid;
        dto.data = run.data;
        dto.metadata = run.metadata;
        dto.trashed = run.trashed;
        if(run.validationErrors != null)
            dto.validationErrors = run.validationErrors.stream().map(RunMapper::fromValidationError).collect(Collectors.toList());
        if(run.datasets != null)
            dto.datasets = run.datasets.stream().map(DataSetMapper::from).collect(Collectors.toList());
        dto.owner = run.owner;
        dto.access = run.access;

        return dto;
    }

    public static Run to(RunDTO dto) {
       Run run = new Run();
       run.id = dto.id;
       run.start = dto.start;
       run.stop = dto.stop;
       run.description = dto.description;
       run.testid = dto.testid;
       run.data = dto.data;
       run.metadata = dto.metadata;
       run.trashed = dto.trashed;
       run.validationErrors = dto.validationErrors.stream().map(RunMapper::toValidationError).collect(Collectors.toList());
       if(dto.datasets != null)
           run.datasets = dto.datasets.stream().map( dsDTO -> DataSetMapper.to(dsDTO, run)).collect(Collectors.toList());

       return run;
    }

    public static ValidationErrorDTO fromValidationError(ValidationError ve) {
        ValidationErrorDTO dto = new ValidationErrorDTO();
        dto.schemaId = ve.getSchemaId();
        dto.error = ve.error;
        return dto;
    }

    public static ValidationError toValidationError(ValidationErrorDTO dto) {
        ValidationError ve = new ValidationError();
        ve.error = dto.error;

        return ve;
    }

}
