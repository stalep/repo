package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.DataPoint;
import io.hyperfoil.tools.horreum.entity.alerting.DataPointDTO;
import io.hyperfoil.tools.horreum.entity.alerting.VariableDTO;
import io.hyperfoil.tools.horreum.entity.json.DataSet;

public class DataPointMapper {

    public static DataPointDTO from(DataPoint dp) {
        DataPointDTO dto = new DataPointDTO();
        dto.id = dp.id;
        dto.timestamp = dp.timestamp;
        dto.value = dp.value;
        if(dp.variable != null)
            dto.variable = VariableMapper.from(dp.variable);
        dto.datasetId = dp.getDatasetId();

        return dto;
    }

    public static DataPoint to(DataPointDTO dto) {
        DataPoint dp = new DataPoint();
        dp.id = dto.id;
        dp.value = dto.value;
        dp.timestamp = dto.timestamp;
        DataSet ds = new DataSet();
        ds.id = dto.datasetId;
        dp.dataset = ds;
        if(dto.variable != null)
            dp.variable = VariableMapper.to(dto.variable);

        return dp;
    }
}
