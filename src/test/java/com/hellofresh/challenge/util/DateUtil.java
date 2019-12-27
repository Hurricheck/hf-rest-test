package com.hellofresh.challenge.util;

import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@NoArgsConstructor
public class DateUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public String getFutureDate(int daysFromNow) {
        Date currentDate = new Date();
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(daysFromNow);
        Date futureDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return dateFormat.format(futureDate);
    }
}
