package org.example.bodega;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ValidadorFecha {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    public static boolean isValid(String fecha) {
        try {
            LocalDate.parse(fecha, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
