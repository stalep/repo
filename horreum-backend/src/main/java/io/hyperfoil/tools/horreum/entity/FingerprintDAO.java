package io.hyperfoil.tools.horreum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.databind.JsonNode;

import io.hyperfoil.tools.horreum.entity.data.DataSetDAO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.type.SqlTypes;

@Entity(name = "Fingerprint")
@Immutable
public class FingerprintDAO extends PanacheEntityBase {
   @Id
   @Column(name = "dataset_id")
   public int datasetId;

   @OneToOne(fetch = FetchType.LAZY)
   @MapsId
   @JoinColumn(name = "dataset_id")
   public DataSetDAO dataset;

   @JdbcTypeCode( SqlTypes.JSON )
   public JsonNode fingerprint;

}
