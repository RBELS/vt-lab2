package org.adbs.vtlabs.lab2new.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class EStorePrincipal {
    @JsonProperty("sub")
    private Long profileId;
    @JsonProperty("username")
    private String username;
    @JsonProperty("authorities")
    private Set<String> authorities;
    @JsonProperty("iss")
    private String iss;
}
