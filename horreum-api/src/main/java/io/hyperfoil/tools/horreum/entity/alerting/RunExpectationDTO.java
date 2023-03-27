package io.hyperfoil.tools.horreum.entity.alerting;

import java.time.Instant;

public class RunExpectationDTO {
    public Long id;
    public int testId;
    public Instant expectedBefore;
    public String expectedBy;
    public String backlink;

}
