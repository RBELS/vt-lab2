package org.adbs.vtlabs.lab2new.components;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public class ObjectMapperProvider {
    private static ObjectMapper instance;
    public static synchronized ObjectMapper getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ObjectMapper();
        }
        return instance;
    }
}
