package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.DataPointDAO;
import io.hyperfoil.tools.horreum.api.alerting.DataPointDTO;
import io.hyperfoil.tools.horreum.entity.data.DataSetDAO;

public class DataPointMapper {

    public static DataPointDTO from(DataPointDAO dp) {
        DataPointDTO dto = new DataPointDTO();
        dto.id = dp.id;
        dto.timestamp = dp.timestamp;
        dto.value = dp.value;
        if(dp.variable != null)
            dto.variable = VariableMapper.from(dp.variable);
        dto.datasetId = dp.getDatasetId();

        return dto;
    }

    public static DataPointDAO to(DataPointDTO dto) {
        DataPointDAO dp = new DataPointDAO();
        dp.id = dto.id;
        dp.value = dto.value;
        dp.timestamp = dto.timestamp;
        DataSetDAO ds = new DataSetDAO();
        ds.id = dto.datasetId;
        dp.dataset = ds;
        if(dto.variable != null)
            dp.variable = VariableMapper.to(dto.variable);

        return dp;
    }
}
