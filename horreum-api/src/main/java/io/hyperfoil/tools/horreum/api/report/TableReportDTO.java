package io.hyperfoil.tools.horreum.api.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;
import java.util.Collection;

public class TableReportDTO {
    @JsonProperty(required = true)
    public Integer id;
    @JsonProperty(required = true)
    public TableReportConfigDTO config;
    @Schema(required = true, type = SchemaType.NUMBER)
    public Instant created;
    public Collection<ReportCommentDTO> comments;
    public Collection<DataDTO> data;
    public Collection<ReportLogDTO> logs;

    public TableReportDTO() {
    }

    @Override
    public String toString() {
        return "TableReportDTO{" +
                "id=" + id +
                ", config=" + config +
                ", created=" + created +
                ", comments=" + comments +
                ", data=" + data +
                ", logs=" + logs +
                '}';
    }

    @Schema(name = "TableReportData")
    public static class DataDTO {
        public int datasetId;
        public int runId;
        public int ordinal;
        public String category;
        public String series;
        public String scale;
        public ArrayNode values;

        public DataDTO() {
        }

        public String toString() {
            return "TableReportDTO.Data{datasetId=" + this.datasetId + ", category='" + this.category + '\'' + ", series='" + this.series + '\'' + ", label='" + this.scale + '\'' + ", values=" + this.values + '}';
        }
    }
}
