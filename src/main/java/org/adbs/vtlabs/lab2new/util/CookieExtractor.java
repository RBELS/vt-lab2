package org.adbs.vtlabs.lab2new.util;

import jakarta.servlet.http.Cookie;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class CookieExtractor {
    public static Optional<String> extractLang(Cookie[] cookies) {
        return Arrays.stream(cookies)
                .filter(cookie -> Objects.equals(cookie.getName(), "lang"))
                .map(Cookie::getValue)
                .findFirst();
    }
}
