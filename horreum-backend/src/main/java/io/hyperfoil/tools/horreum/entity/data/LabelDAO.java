package io.hyperfoil.tools.horreum.entity.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.hibernate.type.SqlTypes;

@Entity(name="label")
@RegisterForReflection
public class LabelDAO extends OwnedEntityBase {
   @JsonProperty(required = true)
   @Id
   @GenericGenerator(
         name = "labelIdGenerator",
         strategy = "io.hyperfoil.tools.horreum.entity.SeqIdGenerator",
         parameters = {
               @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "label_id_seq"),
               @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
         }
   )
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "labelIdGenerator")
   public Integer id;

   @NotNull
   public String name;

   @ManyToOne(optional = false)
   @JoinColumn(name = "schema_id")
   @JsonIgnore
   public SchemaDAO schema;

   @NotNull
   @ElementCollection(fetch = FetchType.EAGER)
   @CollectionTable(name = "label_extractors")
   public Collection<Extractor> extractors;

   public String function;

   @NotNull
   public boolean filtering = true;

   @NotNull
   public boolean metrics = true;

   @JsonProperty(value = "schemaId", required = true)
   public int getSchemaId() {
      return schema.id;
   }

   @JsonProperty("schemaId")
   public void setSchema(int schemaId) {
      schema = getEntityManager().getReference(SchemaDAO.class, schemaId);
   }

   @Entity
   @Table(name = "label_values")
   public static class Value extends PanacheEntityBase implements Serializable {
      @Id
      @NotNull
      @Column(name = "dataset_id")
      public int datasetId;

      @Id
      @NotNull
      @Column(name = "label_id")
      public int labelId;

      @JdbcTypeCode( SqlTypes.JSON )
      public JsonNode value;

      @Override
      public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Value value1 = (Value) o;
         return datasetId == value1.datasetId && labelId == value1.labelId && Objects.equals(value, value1.value);
      }

      @Override
      public int hashCode() {
         return Objects.hash(datasetId, labelId, value);
      }

      @Override
      public String toString() {
         return "Value{" +
               "datasetId=" + datasetId +
               ", labelId=" + labelId +
               ", value=" + value +
               '}';
      }
   }
}
