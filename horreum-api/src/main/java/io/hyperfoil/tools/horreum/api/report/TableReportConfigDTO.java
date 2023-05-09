package io.hyperfoil.tools.horreum.api.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.hyperfoil.tools.horreum.api.data.TestDTO;

import java.util.List;

public class TableReportConfigDTO {
    @JsonProperty(required = true)
    public Integer id;
    public String title;
    public TestDTO test;
    public ArrayNode filterLabels;
    public String filterFunction;
    public ArrayNode categoryLabels;
    public String categoryFunction;
    public String categoryFormatter;
    public ArrayNode seriesLabels;
    public String seriesFunction;
    public String seriesFormatter;
    public ArrayNode scaleLabels;
    public String scaleFunction;
    public String scaleFormatter;
    public String scaleDescription;
    public List<ReportComponentDTO> components;

    public TableReportConfigDTO() {
    }
   public void ensureLinked() {
      if (components != null) {
         for (ReportComponentDTO c : components) {
            c.reportId = id;
         }
      }
   }

    @Override
    public String toString() {
        return "TableReportConfigDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", test=" + test +
                ", filterLabels=" + filterLabels +
                ", filterFunction='" + filterFunction + '\'' +
                ", categoryLabels=" + categoryLabels +
                ", categoryFunction='" + categoryFunction + '\'' +
                ", categoryFormatter='" + categoryFormatter + '\'' +
                ", seriesLabels=" + seriesLabels +
                ", seriesFunction='" + seriesFunction + '\'' +
                ", seriesFormatter='" + seriesFormatter + '\'' +
                ", scaleLabels=" + scaleLabels +
                ", scaleFunction='" + scaleFunction + '\'' +
                ", scaleFormatter='" + scaleFormatter + '\'' +
                ", scaleDescription='" + scaleDescription + '\'' +
                ", components=" + components +
                '}';
    }
}