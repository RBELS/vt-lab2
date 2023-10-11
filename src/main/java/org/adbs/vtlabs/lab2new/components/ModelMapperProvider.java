package org.adbs.vtlabs.lab2new.components;

import org.modelmapper.ModelMapper;

import java.util.Objects;

public class ModelMapperProvider {
    private static ModelMapper instance;
    public static synchronized ModelMapper getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ModelMapper();
        }
        return instance;
    }
}
