package it.sunnyvale.jsenewfeature.firstlastdayofmonth;

import java.time.*;

/**
 * FirstLastDayOfMonth
 */
public class FirstLastDayOfMonth {

    public static String[] firstLastDayOfMonth(YearMonth yearMonth) {
        String firstDay = yearMonth.atDay(1).getDayOfWeek().name();
        String lastDay = yearMonth.atEndOfMonth().getDayOfWeek().name();
        return new String[] { firstDay, lastDay };
    }

}