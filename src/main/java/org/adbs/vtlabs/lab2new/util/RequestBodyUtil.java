package org.adbs.vtlabs.lab2new.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RequestBodyUtil {
    public static String getBodyAsString(BufferedReader reader) throws IOException {
        List<String> lines = new ArrayList<>();
        String line;
        while (Objects.nonNull(line = reader.readLine())) {
            lines.add(line);
        }
        return lines.stream().collect(Collectors.joining("\n"));
    }
}
