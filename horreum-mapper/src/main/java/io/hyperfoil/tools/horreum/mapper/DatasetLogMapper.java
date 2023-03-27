package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.DatasetLog;
import io.hyperfoil.tools.horreum.entity.alerting.DatasetLogDTO;

public class DatasetLogMapper {
    public static DatasetLogDTO from(DatasetLog dl) {
        return new DatasetLogDTO(dl.test.id, dl.dataset.id, dl.dataset.ordinal,
                dl.dataset.run.id, dl.level, dl.source, dl.message );
    }
}
