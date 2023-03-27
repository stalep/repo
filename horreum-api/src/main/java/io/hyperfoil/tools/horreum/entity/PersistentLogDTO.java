package io.hyperfoil.tools.horreum.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public abstract class PersistentLogDTO {
   public static final int DEBUG = 0;
   public static final int INFO = 1;
   public static final int WARN = 2;
   public static final int ERROR = 3;

   @JsonProperty(required = true)
   public Long id;

   public int level;

   @Schema(required = true)
   public Instant timestamp;

   public String message;

   public PersistentLogDTO(int level, String message) {
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
