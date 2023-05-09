package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.RunExpectation;
import io.hyperfoil.tools.horreum.api.alerting.RunExpectationDTO;

public class RunExpectationMapper {

    public static RunExpectationDTO from(RunExpectation re) {
        RunExpectationDTO dto = new RunExpectationDTO();
        dto.id = re.id;
        dto.testId = re.testId;
        dto.expectedBy = re.expectedBy;
        dto.expectedBefore = re.expectedBefore;
        dto.backlink = re.backlink;

        return dto;
    }
}
