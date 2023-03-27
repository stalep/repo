package io.hyperfoil.tools.horreum.mapper;

import io.hyperfoil.tools.horreum.entity.alerting.MissingDataRule;
import io.hyperfoil.tools.horreum.entity.alerting.MissingDataRuleDTO;
import io.hyperfoil.tools.horreum.entity.json.Test;

public class MissingDataRuleMapper {
    public static MissingDataRuleDTO from(MissingDataRule rule) {
        MissingDataRuleDTO dto = new MissingDataRuleDTO();
        dto.id = rule.id;
        dto.name = rule.name;
        dto.condition = rule.condition;
        dto.labels = rule.labels;
        dto.lastNotification = rule.lastNotification;
        dto.maxStaleness = rule.maxStaleness;
        dto.testId = rule.testId();

        return dto;
    }

    public static MissingDataRule to(MissingDataRuleDTO dto) {
        MissingDataRule rule = new MissingDataRule();
        rule.id = dto.id;
        rule.name = dto.name;
        rule.condition = dto.condition;
        rule.labels = dto.labels;
        rule.lastNotification = dto.lastNotification;
        rule.maxStaleness = dto.maxStaleness;
        Test test = new Test();
        test.id = dto.testId;
        rule.test = test;

        return rule;
    }
}
