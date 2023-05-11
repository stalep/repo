package io.hyperfoil.tools.horreum.converter;

import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import org.hibernate.dialect.PostgreSQLDialect;

import java.sql.Types;

public class JsonPostgreSQLDialect extends PostgreSQLDialect {

   public JsonPostgreSQLDialect(){

       //stalep: PostgreSQLDialect now adds: new DdlTypeImpl(3001, "jsonb", this) as a dialect, not sure if we need this anymore
      //registerColumnType(Types.JAVA_OBJECT,"jsonb");
      /*
      super();
      this.registerColumnTypes(
              Types.OTHER, JsonNodeBinaryType.class.getName()
      );

       */

   }
}
