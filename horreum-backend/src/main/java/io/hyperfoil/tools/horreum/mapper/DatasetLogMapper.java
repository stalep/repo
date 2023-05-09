package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.DatasetLogDAO;
import io.hyperfoil.tools.horreum.api.alerting.DatasetLogDTO;

public class DatasetLogMapper {
    public static DatasetLogDTO from(DatasetLogDAO dl) {
        return new DatasetLogDTO(dl.test.id, dl.dataset.id, dl.dataset.ordinal,
                dl.dataset.run.id, dl.level, dl.source, dl.message );
    }
}
