package io.hyperfoil.tools.horreum.entity.report;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity(name = "ReportComment")
public class ReportCommentDAO extends PanacheEntityBase {
   @Id
   @GeneratedValue
   public Integer id;

   @JsonIgnore
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "report_id", nullable = false)
   public TableReportDAO report;

   // 0 = root comment, 1 = on category, 2 = on component
   @NotNull
   public int level;

   public String category;

   @Column(name = "component_id")
   public int componentId;

   @NotNull
   public String comment;
}
