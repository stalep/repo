package io.hyperfoil.tools.horreum.entity;

import java.util.Collection;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import io.hyperfoil.tools.horreum.entity.data.TestDAO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.type.SqlTypes;

@Entity(name = "ExperimentProfile")
@Table(name = "experiment_profile")
public class ExperimentProfileDAO extends PanacheEntityBase {
   @JsonProperty(required = true)
   @Id
   @GenericGenerator(
         name = "experimentProfileIdGenerator",
         strategy = "io.hyperfoil.tools.horreum.entity.SeqIdGenerator",
         parameters = {
               @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
         }
   )
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "experimentProfileIdGenerator")
   public Integer id;

   @NotNull
   public String name;

   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
   @JsonIgnore
   public TestDAO test;

   @Schema(implementation = String[].class, required = true)
   @Column(name = "selector_labels")
   @JdbcTypeCode( SqlTypes.JSON )
   public JsonNode selectorLabels;

   @Column(name = "selector_filter")
   public String selectorFilter;

   @Schema(implementation = String[].class, required = true)
   @Column(name = "baseline_labels")
   @JdbcTypeCode( SqlTypes.JSON )
   public JsonNode baselineLabels;

   @Column(name = "baseline_filter")
   public String baselineFilter;

   @JsonProperty(required = true)
   @ElementCollection(fetch = FetchType.EAGER)
   @CollectionTable(name="experiment_comparisons", joinColumns=@JoinColumn(name="profile_id"))
   @OrderBy("variable_id, model")
   public Collection<ExperimentComparison> comparisons;

   /* These labels are not used in Horreum but are added to the result event */
   @Schema(implementation = String[].class)
   @Column(name = "extra_labels")
   @JdbcTypeCode( SqlTypes.JSON )
   public JsonNode extraLabels;

}
