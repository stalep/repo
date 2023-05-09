package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.data.DataSet;
import io.hyperfoil.tools.horreum.api.data.DataSetDTO;
import io.hyperfoil.tools.horreum.entity.data.Run;

public class DataSetMapper {

    public static DataSetDTO from(DataSet ds) {

        DataSetDTO dto = new DataSetDTO();
        dto.id = ds.id;
        dto.runId = ds.getRunId();
        dto.start = ds.start;
        dto.stop = ds.stop;
        dto.testid = ds.testid;
        dto.owner = ds.owner;
        dto.access = ds.access;
        dto.ordinal = ds.ordinal;
        dto.description = ds.description;
        dto.data = ds.data;

        return dto;
    }

    public static DataSet to(DataSetDTO dto, Run run) {
        DataSet ds = new DataSet(run, dto.ordinal, dto.description, dto.data);
        ds.id = dto.id;
        return ds;
    }

    public static DataSetDTO.Info fromInfo(DataSet.Info info) {
        return new DataSetDTO.Info(info.id, info.runId, info.ordinal, info.testId);
    }

    public static DataSet.Info toInfo(DataSetDTO.Info info) {
        return new DataSet.Info(info.id, info.runId, info.ordinal, info.testId);
    }
}
