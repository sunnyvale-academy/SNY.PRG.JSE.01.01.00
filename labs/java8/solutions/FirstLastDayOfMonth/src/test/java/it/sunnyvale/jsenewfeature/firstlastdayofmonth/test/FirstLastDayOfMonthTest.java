package it.sunnyvale.jsenewfeature.firstlastdayofmonth.test;
        
import static org.junit.Assert.assertTrue;
import it.sunnyvale.jsenewfeature.firstlastdayofmonth.FirstLastDayOfMonth;

import java.time.YearMonth;


import org.junit.Test;

public class FirstLastDayOfMonthTest {

    @Test
    public void testFormatLocalDate() {
        YearMonth ym = YearMonth.now();
        String firstDay = ym.atDay(1).getDayOfWeek().name();
        String lastDay = ym.atEndOfMonth().getDayOfWeek().name();
        String[] arr = FirstLastDayOfMonth.firstLastDayOfMonth(ym);
        String firstDayByClass = arr[0];
        String lastDayByClass = arr[1];
        assertTrue(firstDay.matches(firstDayByClass));
        assertTrue(lastDay.matches(lastDayByClass));
    }
    
}
