package org.adbs.vtlabs.lab2new.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostRegisterBodyDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
