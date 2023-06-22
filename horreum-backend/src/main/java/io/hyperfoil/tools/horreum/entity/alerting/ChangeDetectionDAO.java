package io.hyperfoil.tools.horreum.entity.alerting;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.type.SqlTypes;

@Entity(name = "ChangeDetection")
public class ChangeDetectionDAO extends PanacheEntityBase {
   @JsonProperty(required = true)
   @Id
   @GenericGenerator(
         name = "changeDetectionIdGenerator",
         strategy = "io.hyperfoil.tools.horreum.entity.SeqIdGenerator",
         parameters = {
               @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
         }
   )
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "changeDetectionIdGenerator")
   public Integer id;

   @NotNull
   public String model;

   @NotNull
   @JdbcTypeCode( SqlTypes.JSON )
   public JsonNode config;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "variable_id")
   @JsonIgnore
   public VariableDAO variable;

   @Override
   public String toString() {
      return "ChangeDetection{" +
            "id=" + id +
            ", model='" + model + '\'' +
            ", config=" + config +
            '}';
   }
}
