package io.hyperfoil.tools.horreum.action;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.enterprise.context.ApplicationScoped;

import io.hyperfoil.tools.horreum.entity.alerting.ChangeDTO;
import io.hyperfoil.tools.horreum.entity.json.DataSet;
import io.hyperfoil.tools.horreum.mapper.ChangeMapper;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.fasterxml.jackson.databind.JsonNode;

import io.hyperfoil.tools.horreum.entity.alerting.Change;
import io.hyperfoil.tools.horreum.entity.json.Test;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;

@ApplicationScoped
public class ChangeToMarkdown implements BodyFormatter {
   @Location("new_issue_from_change")
   Template template;

   @ConfigProperty(name = "horreum.url")
   String publicUrl;

   @Override
   public String name() {
      return "changeToMarkdown";
   }

   @Override
   public String format(JsonNode config, Object payload) {
      if (!(payload instanceof ChangeDTO.Event)) {
         throw new IllegalArgumentException("This formatter accepts only Change.Event!");
      }
      ChangeDTO.Event event = (ChangeDTO.Event) payload;
      ChangeDTO change = event.change;
      String fingerprint = DataSet.getEntityManager().getReference(DataSet.class, change.dataset.id).getFingerprint();
      return template
            .data("testName", event.testName)
            .data("testNameEncoded", URLEncoder.encode(event.testName, StandardCharsets.UTF_8))
            .data("fingerprint", URLEncoder.encode(fingerprint, StandardCharsets.UTF_8))
            .data("publicUrl", publicUrl)
            .data("testId", String.valueOf(change.variable.testId))
            .data("variable", change.variable.name)
            .data("group", change.variable.group)
            .data("runId", event.dataset.runId)
            .data("datasetOrdinal", event.dataset.ordinal)
            .data("description", change.description)
            .render();
   }
}
