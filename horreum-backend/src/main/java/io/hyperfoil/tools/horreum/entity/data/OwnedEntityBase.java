package io.hyperfoil.tools.horreum.entity.data;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import io.hyperfoil.tools.horreum.api.data.Access;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@RegisterForReflection
@MappedSuperclass
public class OwnedEntityBase extends PanacheEntityBase {

   @NotNull
   public String owner;

   @NotNull
   @Column(columnDefinition = "INTEGER")
   @JdbcTypeCode(SqlTypes.INTEGER)
   public Access access = Access.PUBLIC;

}
