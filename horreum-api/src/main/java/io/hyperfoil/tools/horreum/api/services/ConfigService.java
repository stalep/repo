package io.hyperfoil.tools.horreum.api.services;

import io.hyperfoil.tools.horreum.api.Version;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.ConfigProvider;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkus.runtime.Startup;

@Startup
@PermitAll
@Path("/api/config")
@Produces(MediaType.APPLICATION_JSON)
public class ConfigService {
   long startTimestamp = System.currentTimeMillis();

   @GET
   @Path("keycloak")
   public KeycloakConfig keycloak() {
      KeycloakConfig config = new KeycloakConfig();
      config.url = getString("horreum.keycloak.url");
      return config;
   }

   @GET
   @Path("version")
   public VersionInfo version() {
      VersionInfo info = new VersionInfo();
      info.version = Version.VERSION;
      info.startTimestamp = startTimestamp;
      return info;
   }

   private String getString(String propertyName) {
      return ConfigProvider.getConfig().getValue(propertyName, String.class);
   }

   public static class VersionInfo {
      @NotNull
      public String version;
      @JsonProperty(required = true)
      public long startTimestamp;
   }

   public static class KeycloakConfig {
      public String realm = "horreum";
      public String url;
      public String clientId = "horreum-ui";
   }
}
