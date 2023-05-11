package io.hyperfoil.tools.horreum.entity.converter;

import io.hyperfoil.tools.horreum.api.ApiUtil;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.json.Json;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

@RegisterForReflection
public class JsonUserType implements UserType<JsonNode> {

   @Override
   public int getSqlType() {
      return Types.JAVA_OBJECT;
   }

   @Override
   public Class<JsonNode> returnedClass() {
      return JsonNode.class;
   }

   @Override
   public boolean equals(JsonNode o1, JsonNode o2) throws HibernateException {
      if (o1 == o2) {
         return true;
      } else if (o1 == null || o2 == null){
         return false;
      }
      return o1.equals(o2);
   }

   @Override
   public int hashCode(JsonNode o) throws HibernateException {
      if(o == null){
         return 0;
      }
      return o.hashCode();
   }

   @Override
   public JsonNode nullSafeGet(ResultSet resultSet, int i, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
      String content = (String) resultSet.getObject(i);
      if (content == null) {
         return null;
      }
      try {
         return ApiUtil.OBJECT_MAPPER.readTree(content);
      } catch (JsonProcessingException e) {
         throw new HibernateException(e);
      }
   }

   @Override
   public void nullSafeSet(PreparedStatement preparedStatement, JsonNode o, int i,
                           SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
      if (o == null) {
         preparedStatement.setNull(i, Types.OTHER);
         return;
      }
      try {
         preparedStatement.setObject(i, o.toString().replaceAll("\n", ""), Types.OTHER);
      } catch (Exception e) {
         throw new HibernateException(e);
      }
   }

   @Override
   public JsonNode deepCopy(JsonNode o) throws HibernateException {
      if(o != null)
         return o.deepCopy();
      else
         return null;
   }

   @Override
   public boolean isMutable() {
      return false;
   }

   @Override
   public Serializable disassemble(JsonNode o) throws HibernateException {
      if(o == null){
         return null;
      }
      return o.toString().replaceAll("\n","");
   }

   @Override
   public JsonNode assemble(Serializable cached, Object owner) throws HibernateException {
      if (cached instanceof String) {
         try {
            return ApiUtil.OBJECT_MAPPER.readTree((String) cached);
         } catch (JsonProcessingException e) {
            throw new HibernateException(e);
         }
      }
      return null;
   }

   @Override
   public JsonNode replace(JsonNode original, JsonNode target, Object owner) throws HibernateException {
      return original;
   }
}
