package io.hyperfoil.tools.horreum.entity.report;

import com.fasterxml.jackson.databind.node.ArrayNode;

public class ReportComponentDTO {
    public Integer id;
    public String name;
    public int order;
    public ArrayNode labels;
    public String function;
    public String unit;
    public Integer reportId;

}
