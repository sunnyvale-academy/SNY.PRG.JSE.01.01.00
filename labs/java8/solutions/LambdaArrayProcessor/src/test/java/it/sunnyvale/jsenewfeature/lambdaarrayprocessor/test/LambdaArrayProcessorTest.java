package it.sunnyvale.jsenewfeature.lambdaarrayprocessor.test;
        
import static org.junit.Assert.assertTrue;
import it.sunnyvale.jsenewfeature.lambdaarrayprocessor.LambdaArrayProcessor;
import it.sunnyvale.jsenewfeature.lambdaarrayprocessor.ArrayProcessor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.Test;

public class LambdaArrayProcessorTest {

    private double[] array = { 17.0, 3.14, 17.0, -3.4, 17.0, 42.0, 29.2, 3.14 };

    @Test
    public void testLambdaArrayProcessorMax() {
        ArrayProcessor maxer = LambdaArrayProcessor.getMaxer();
        double max = maxer.apply(array);
        assertTrue(42.0 == max);
       
    }

    @Test
    public void testLambdaArrayProcessorMin() {
        ArrayProcessor miner = LambdaArrayProcessor.getMiner();
        double min = miner.apply(array);
        assertTrue(-3.4 == min);
    }
    
}
