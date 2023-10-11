package org.adbs.vtlabs.lab2new.model.service;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private Long userId;
    private String username;
    private String hash;
}
