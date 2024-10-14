package org.adswizz.podcast.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {
    public static String formatLocalDateTimeInUTC(long epochTime) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(epochTime).atZone(ZoneId.of("UTC")).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern("E HH:mm", Locale.ENGLISH));
    }
}
