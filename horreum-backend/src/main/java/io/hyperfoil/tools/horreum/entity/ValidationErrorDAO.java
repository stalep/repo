package io.hyperfoil.tools.horreum.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import io.hyperfoil.tools.horreum.entity.data.SchemaDAO;
import org.hibernate.type.SqlTypes;

@Embeddable
public class ValidationErrorDAO {
   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JsonIgnore
   public SchemaDAO schema;

   @NotNull
   @JdbcTypeCode( SqlTypes.JSON )
   public JsonNode error;

   @JsonProperty(value = "schemaId", required = true)
   public void setSchema(int id) {
      schema = SchemaDAO.getEntityManager().getReference(SchemaDAO.class, id);
   }

   @JsonProperty(value = "schemaId", required = true)
   public Integer getSchemaId() {
      return schema == null ? null : schema.id;
   }
}
