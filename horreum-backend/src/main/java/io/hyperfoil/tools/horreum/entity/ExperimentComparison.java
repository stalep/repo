package io.hyperfoil.tools.horreum.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import io.hyperfoil.tools.horreum.entity.alerting.VariableDAO;
import org.hibernate.type.SqlTypes;

@Embeddable
public class ExperimentComparison {
   @NotNull
   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   @JoinColumn(name = "variable_id")
   @JsonIgnore
   public VariableDAO variable;

   @NotNull
   public String model;

   @NotNull
   @JdbcTypeCode( SqlTypes.JSON )
   public JsonNode config;

   @JsonProperty("variableId")
   public void setVariableId(int id) {
      variable = VariableDAO.getEntityManager().getReference(VariableDAO.class, id);
   }

   @JsonProperty(value = "variableId", required = true)
   public int getVariableId() {
      return variable.id;
   }
}
