package io.hyperfoil.tools.horreum.api.report;

import com.fasterxml.jackson.databind.node.ArrayNode;

public class ReportComponent {
    public Integer id;
    public String name;
    public int order;
    public ArrayNode labels;
    public String function;
    public String unit;
    public Integer reportId;

}
