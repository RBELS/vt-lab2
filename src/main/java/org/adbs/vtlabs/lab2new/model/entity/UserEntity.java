package org.adbs.vtlabs.lab2new.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserEntity {
    public static String COLUMN_USER_ID = "USER_ID";
    public static String COLUMN_USERNAME = "USERNAME";
    public static String COLUMN_HASH = "HASH";

    private Long userId;
    private String username;
    private String hash;
}
