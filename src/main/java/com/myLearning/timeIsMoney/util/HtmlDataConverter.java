package com.mylearning.timeismoney.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HtmlDataConverter {

    public static LocalDateTime parseToLocalDateTime(String htmlInputData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        return LocalDateTime.parse(htmlInputData, formatter);
    }


}
