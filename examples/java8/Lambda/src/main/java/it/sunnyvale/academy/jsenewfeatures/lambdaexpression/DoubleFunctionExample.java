package it.sunnyvale.academy.jsenewfeatures.lambdaexpression;

import java.util.function.DoubleFunction;

/**
 * DoubleFunctionExample
 */
public class DoubleFunctionExample {

    public static void main(String[] args) {
        DoubleFunction<String> calc = t -> String.valueOf(t * 3);

        String result = calc.apply(20);
        System.out.println("New value is: " + result);
    }
}