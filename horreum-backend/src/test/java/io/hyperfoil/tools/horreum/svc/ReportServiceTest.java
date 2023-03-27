package io.hyperfoil.tools.horreum.svc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.hyperfoil.tools.horreum.entity.json.*;
import io.hyperfoil.tools.horreum.entity.report.*;
import io.hyperfoil.tools.horreum.test.NoGrafanaProfile;
import io.hyperfoil.tools.horreum.test.PostgresResource;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.oidc.server.OidcWiremockTestResource;

@QuarkusTest
@QuarkusTestResource(PostgresResource.class)
@QuarkusTestResource(OidcWiremockTestResource.class)
@TestProfile(NoGrafanaProfile.class)
public class ReportServiceTest extends BaseServiceTest {
   private static final String SCHEMA = "urn:comparison";

   @org.junit.jupiter.api.Test
   public void testNoFilter() throws InterruptedException {
      TestDTO test = createTest(createExampleTest("nofilter"));
      createComparisonSchema();
      uploadExampleRuns(test);

      TableReportConfigDTO config = newExampleTableReportConfig(test);
      TableReportDTO report = jsonRequest().body(config).post("/api/report/table/config")
            .then().statusCode(200).extract().body().as(TableReportDTO.class);

      assertEquals(8, report.data.size());
      assertCount(report, 4, d -> d.category, "jvm");
      assertCount(report, 4, d -> d.category, "native");
      assertCount(report, 4, d -> d.series, "windows");
      assertCount(report, 4, d -> d.series, "linux");
      TableReportDTO.DataDTO duplicated = report.data.stream()
            .filter(d -> "windows".equals(d.series) && "jvm".equals(d.category) && Integer.parseInt(d.scale) == 2)
            .findFirst().orElseThrow();
      assertEquals(0.4, duplicated.values.get(0).asDouble());
      assertEquals(120_000_000L, duplicated.values.get(1).asLong());
      assertEquals(256, duplicated.values.get(2).asInt());

      deleteReport(report);
   }

   @org.junit.jupiter.api.Test
   public void testFilter() throws InterruptedException {
      TestDTO test = createTest(createExampleTest("filter"));
      createComparisonSchema();
      uploadExampleRuns(test);

      TableReportConfigDTO config = newExampleTableReportConfig(test);
      config.filterLabels = arrayOf("variant");
      config.filterFunction = "v => v === 'production'";
      TableReportDTO report = jsonRequest().body(config).post("/api/report/table/config")
            .then().statusCode(200).extract().body().as(TableReportDTO.class);

      assertEquals(6, report.data.size());
      assertCount(report, 3, d -> d.category, "jvm");
      assertCount(report, 3, d -> d.category, "native");
      assertCount(report, 4, d -> d.series, "windows");
      assertCount(report, 2, d -> d.series, "linux");
      TableReportDTO.DataDTO duplicated = report.data.stream()
            .filter(d -> "windows".equals(d.series) && "jvm".equals(d.category) && Integer.parseInt(d.scale) == 2)
            .findFirst().orElseThrow();
      assertEquals(0.8, duplicated.values.get(0).asDouble());
      assertEquals(150_000_000L, duplicated.values.get(1).asLong());
      assertEquals(200, duplicated.values.get(2).asInt());

      ReportCommentDTO comment = createComment(2, "native", "Hello world");
      ReportCommentDTO commentWithId = jsonRequest().body(comment).post("/api/report/comment/" + report.id)
            .then().statusCode(200).extract().body().as(ReportCommentDTO.class);
      TableReportDTO updatedReport = jsonRequest().get("/api/report/table/" + report.id)
            .then().statusCode(200).extract().body().as(TableReportDTO.class);
      assertEquals(1, updatedReport.comments.size());
      ReportCommentDTO readComment = updatedReport.comments.stream().findFirst().orElseThrow();
      assertEquals(commentWithId.id, readComment.id);
      assertEquals(2, readComment.level);
      assertEquals("native", readComment.category);
      assertEquals("Hello world", readComment.comment);

      commentWithId.comment = "Nazdar";
      jsonRequest().body(commentWithId).post("/api/report/comment/" + report.id);
      comment.comment = "Ahoj";
      comment.category = "jvm";
      jsonRequest().body(comment).post("/api/report/comment/" + report.id); // no comment ID => add

      updatedReport = jsonRequest().get("/api/report/table/" + report.id)
            .then().statusCode(200).extract().body().as(TableReportDTO.class);
      assertEquals(2, updatedReport.comments.size());
      assertEquals(1, updatedReport.comments.stream().filter(c -> "Nazdar".equals(c.comment)).count());
      assertEquals(1, updatedReport.comments.stream().filter(c -> "Ahoj".equals(c.comment)).count());

      deleteReport(report);
   }

   private ReportCommentDTO createComment(int level, String category, String msg) {
      ReportCommentDTO comment = new ReportCommentDTO();
      comment.level = 2;
      comment.category = category;
      comment.componentId = 1;
      comment.comment = msg;
      return comment;
   }

   private void deleteReport(TableReportDTO report) {
      jsonRequest().delete("/api/report/table/" + report.id).then().statusCode(204);
   }

   private TableReportConfigDTO newExampleTableReportConfig(TestDTO test) {
      TableReportConfigDTO config = new TableReportConfigDTO();
      config.test = test;
      config.title = "Test no filter";
      config.categoryLabels = arrayOf("category");
      // category is used just for the sake of testing two labels
      config.seriesLabels = arrayOf("os", "category");
      config.seriesFunction = "({ os, category }) => os";
      config.scaleLabels = arrayOf("clusterSize");
      config.components = new ArrayList<>();
      config.components.add(newComponent(null, "cpuUsage"));
      config.components.add(newComponent(null, "memoryUsage"));
      config.components.add(newComponent(null, "throughput"));
      return config;
   }

   private void uploadExampleRuns(TestDTO test) throws InterruptedException {
      BlockingQueue<DataSet.LabelsUpdatedEvent> queue = eventConsumerQueue(DataSet.LabelsUpdatedEvent.class, DataSet.EVENT_LABELS_UPDATED, e -> checkTestId(e.datasetId, test.id));

      long ts = System.currentTimeMillis();
      uploadRun(ts - 1, createRunData("production", "windows", "jvm", 1, 0.5, 150_000_000, 123) , test.name);
      uploadRun(ts - 2, createRunData("debug", "windows", "jvm", 2, 0.4, 120_000_000, 256) , test.name);
      uploadRun(ts - 3, createRunData("production", "linux", "jvm", 1, 0.4, 100_000_000, 135) , test.name);
      uploadRun(ts - 4, createRunData("debug", "linux", "jvm", 2, 0.3, 80_000_000, 260) , test.name);
      uploadRun(ts - 5, createRunData("production", "windows", "native", 1, 0.4, 50_000_000, 100) , test.name);
      uploadRun(ts - 6, createRunData("production", "windows", "native", 2, 0.3, 40_000_000, 200) , test.name);
      uploadRun(ts - 7, createRunData("production", "linux", "native", 1, 0.3, 30_000_000, 110) , test.name);
      uploadRun(ts - 8, createRunData("debug", "linux", "native", 2, 0.28, 20_000_000, 210) , test.name);
      // some older run that should be ignored
      uploadRun(ts - 9, createRunData("production", "windows", "jvm", 2, 0.8, 150_000_000, 200) , test.name);

      for (int i = 0; i < 9; ++i) {
         assertNotNull(queue.poll(1, TimeUnit.SECONDS));
      }
   }

   private void assertCount(TableReportDTO report, int expected, Function<TableReportDTO.DataDTO, String> selector, String value) {
      assertEquals(expected, report.data.stream().map(selector).filter(value::equals).count());
   }

   private ReportComponentDTO newComponent(String function, String... labels) {
      ReportComponentDTO component = new ReportComponentDTO();
      component.name = Stream.of(labels).map(l -> Character.toUpperCase(l.charAt(0)) + l.substring(1)).collect(Collectors.joining("+"));
      component.labels = arrayOf(labels);
      component.function = function;
      return component;
   }

   private ArrayNode arrayOf(String... labels) {
      ArrayNode array = JsonNodeFactory.instance.arrayNode();
      for (String label : labels) {
         array.add(label);
      }
      return array;
   }

   private JsonNode createRunData(String variant, String os, String category, int clusterSize, double cpuUsage, long memoryUsage, long throughput) {
      ObjectNode data = JsonNodeFactory.instance.objectNode();
      return data.put("$schema", SCHEMA)
            .put("variant", variant)
            .put("os", os)
            .put("category", category)
            .put("clusterSize", clusterSize)
            .put("cpuUsage", cpuUsage)
            .put("memoryUsage", memoryUsage)
            .put("throughput", throughput);
   }

   private void createComparisonSchema() {
      SchemaDTO schema = createSchema("comparison", SCHEMA);
      addLabel(schema, "variant", null, new ExtractorDTO("variant", "$.variant", false));
      addLabel(schema, "os", null, new ExtractorDTO("os", "$.os", false));
      addLabel(schema, "category", null, new ExtractorDTO("category", "$.category", false));
      addLabel(schema, "clusterSize", null, new ExtractorDTO("clusterSize", "$.clusterSize", false));
      addLabel(schema, "cpuUsage", null, new ExtractorDTO("cpuUsage", "$.cpuUsage", false));
      addLabel(schema, "memoryUsage", null, new ExtractorDTO("memoryUsage", "$.memoryUsage", false));
      addLabel(schema, "throughput", null, new ExtractorDTO("throughput", "$.throughput", false));
   }

   @org.junit.jupiter.api.Test
   public void testMissingValues() throws InterruptedException {
      TestDTO test = createTest(createExampleTest("missing"));
      createComparisonSchema();

      BlockingQueue<DataSet.LabelsUpdatedEvent> queue = eventConsumerQueue(DataSet.LabelsUpdatedEvent.class, DataSet.EVENT_LABELS_UPDATED, e -> checkTestId(e.datasetId, test.id));
      int runId = uploadRun(JsonNodeFactory.instance.objectNode(), test.name);
      assertNotNull(queue.poll(10, TimeUnit.SECONDS));

      TableReportConfigDTO config = newExampleTableReportConfig(test);
      TableReportDTO report = jsonRequest().body(config).post("/api/report/table/config")
            .then().statusCode(200).extract().body().as(TableReportDTO.class);

      assertEquals(1, report.data.size());
      TableReportDTO.DataDTO data = report.data.iterator().next();
      assertEquals(runId, data.runId);
      assertEquals("", data.series);
      assertEquals("", data.category);
      assertEquals("", data.scale);
      assertEquals(3, data.values.size());
      data.values.forEach(value -> assertTrue(value.isNull()));
   }
}
