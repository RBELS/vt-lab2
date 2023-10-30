package org.adbs.vtlabs.lab2new.exception;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ErrorData {
    private String message;
    private Integer code;
}
