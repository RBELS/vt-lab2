package org.adbs.vtlabs.lab2new.exception;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCode {
    INTERNAL_ERROR,
    DATABASE_ERROR,
    BOOK_NOT_FOUND,
    BAD_REQUEST,
    LOGIN_ERROR,
    REGISTER_ERROR,
    PAGE_NOT_FOUND;

    public static final Map<ErrorCode, ErrorData> ERROR_DATA;
    static {
        ERROR_DATA = new HashMap<>();
        ERROR_DATA.put(INTERNAL_ERROR, new ErrorData()
                .setCode(500)
                .setMessage("Internal server error")
        );
        ERROR_DATA.put(DATABASE_ERROR, new ErrorData()
                .setCode(500)
                .setMessage("Database error")
        );
        ERROR_DATA.put(BOOK_NOT_FOUND, new ErrorData()
                .setCode(404)
                .setMessage("Book not found")
        );
        ERROR_DATA.put(BAD_REQUEST, new ErrorData()
                .setCode(400)
                .setMessage("Bad request")
        );
        ERROR_DATA.put(LOGIN_ERROR, new ErrorData()
                .setCode(400)
                .setMessage("Authorization error")
        );
        ERROR_DATA.put(REGISTER_ERROR, new ErrorData()
                .setCode(400)
                .setMessage("Registration error")
        );
        ERROR_DATA.put(PAGE_NOT_FOUND, new ErrorData()
                .setCode(404)
                .setMessage("Page not found")
        );
    }
}
