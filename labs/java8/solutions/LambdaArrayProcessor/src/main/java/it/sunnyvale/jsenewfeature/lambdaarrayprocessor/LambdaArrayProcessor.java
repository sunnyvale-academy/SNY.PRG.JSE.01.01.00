package it.sunnyvale.jsenewfeature.lambdaarrayprocessor;

import java.time.*;
import java.time.format.*;

/**
 * LambdaArrayProcessor
 */
public class LambdaArrayProcessor {

    private static final ArrayProcessor maxer = array -> {
        double max = array[0];
        for (int i = 0; i < array.length; i++) {
            if ( array[i] > max)
                max = array[i];
        }
        return max;
    };

    private static final ArrayProcessor miner = array -> {
        double min = array[0];
        for (int i = 0; i < array.length; i++) {
            if ( array[i] < min)
                min = array[i];
        }
        return min;
    };

    public static ArrayProcessor getMaxer(){
        return LambdaArrayProcessor.maxer;
    }

    public static ArrayProcessor getMiner(){
        return LambdaArrayProcessor.miner;
    }

}