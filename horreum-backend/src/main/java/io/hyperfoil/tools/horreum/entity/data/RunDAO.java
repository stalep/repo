package io.hyperfoil.tools.horreum.entity.data;

import io.hyperfoil.tools.horreum.entity.ValidationErrorDAO;
import io.quarkus.runtime.annotations.RegisterForReflection;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.type.SqlTypes;

@Entity(name = "run")
@RegisterForReflection
@DynamicUpdate // We don't want to trigger schema analysis when trashing the run
public class RunDAO extends ProtectedBaseEntity {
   public static final String EVENT_NEW = "run/new";
   public static final String EVENT_TRASHED = "run/trashed";
   public static final String EVENT_VALIDATED = "run/validated";

   @JsonProperty(required = true)
   @Id
   @SequenceGenerator(
      name = "runSequence",
      sequenceName = "run_id_seq",
      allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "runSequence")
   public Integer id;

   @Schema(type = SchemaType.NUMBER)
   @NotNull
   @Column(name="start", columnDefinition = "timestamp")
   public Instant start;

   @Schema(type = SchemaType.NUMBER)
   @NotNull
   @Column(name="stop", columnDefinition = "timestamp")
   public Instant stop;

   public String description;

   @NotNull
   public Integer testid;

   @NotNull
   @JdbcTypeCode( SqlTypes.JSON )
   public JsonNode data;

   @JdbcTypeCode( SqlTypes.JSON )
   public JsonNode metadata;

   @NotNull
   @Column(columnDefinition = "boolean default false")
   public boolean trashed;

   @OneToMany(mappedBy = "run", cascade = CascadeType.ALL, orphanRemoval = true)
   @JsonIgnore
   public Collection<DataSetDAO> datasets;

   @CollectionTable
   @ElementCollection
   public Collection<ValidationErrorDAO> validationErrors;
}
