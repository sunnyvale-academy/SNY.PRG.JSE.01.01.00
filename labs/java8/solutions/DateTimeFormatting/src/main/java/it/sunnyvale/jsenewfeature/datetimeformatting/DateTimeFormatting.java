package it.sunnyvale.jsenewfeature.datetimeformatting;

import java.time.*;
import java.time.format.*;


/**
 * DateParseFormatExercise
 */
public class DateTimeFormatting {

    public static String format(LocalDate localDate){
        return localDate.format(DateTimeFormatter.ofPattern("d::MMM::uuuu"));
    }

    public static String format(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss"));
    }

    public static String format(Instant instant){
        return instant.toString();
    }
    
    public static String format(ZonedDateTime zonedDateTime){
        return zonedDateTime.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss z"));
    }


    
}