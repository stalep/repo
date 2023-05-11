package io.hyperfoil.tools.horreum.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@MappedSuperclass
public abstract class PersistentLog extends PanacheEntityBase {
   public static final int DEBUG = 0;
   public static final int INFO = 1;
   public static final int WARN = 2;
   public static final int ERROR = 3;

   @JsonProperty(required = true)
   @Id
   @GeneratedValue
   public Long id;

   @NotNull
   public int level;

   @JsonProperty(required = true)
   @NotNull
   @Column(columnDefinition = "timestamp")
   public Instant timestamp;

   @NotNull
   public String message;

   public PersistentLog(int level, String message) {
      this.level = level;
      this.message = message;
      this.timestamp = Instant.now();
   }

   public static Logger.Level logLevel(int level) {
      switch (level) {
         case DEBUG:
            return Logger.Level.DEBUG;
         case INFO:
            return Logger.Level.INFO;
         case WARN:
            return Logger.Level.WARN;
         case ERROR:
         default:
            return Logger.Level.ERROR;
      }
   }
}
