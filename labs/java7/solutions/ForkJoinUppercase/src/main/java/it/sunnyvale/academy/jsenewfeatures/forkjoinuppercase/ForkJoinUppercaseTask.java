/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sunnyvale.academy.jsenewfeatures.forkjoinuppercase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Logger;

/**
 *
 * @author denismaggiorotto
 */
public class ForkJoinUppercaseTask extends RecursiveTask<String> {

    private String workload;
    private int threshold;
    private int start;
    private int end;

    private static Logger logger
            = Logger.getAnonymousLogger();

    public ForkJoinUppercaseTask(String workload, int start, int end, int threshold) {
        this.workload = workload;
        this.threshold = threshold;
        this.start = start;
        this.end = end;
    }

    @Override
    protected String compute() {

        if (end - start < threshold) {

            return processing(workload);

        } else {

            String partOne = workload.substring(0, workload.length() / 2);
            String partTwo = workload.substring(workload.length() / 2, workload.length());

            ForkJoinUppercaseTask t1 = new ForkJoinUppercaseTask(partOne, 0, partOne.length() - 1, threshold);
            t1.fork();

            ForkJoinUppercaseTask t2 = new ForkJoinUppercaseTask(partTwo, 0, partTwo.length() - 1, threshold);

            return t2.compute() + t1.join();

        }
    }

    private String processing(String work) {
        String result = work.toUpperCase();
        //logger.info("This result - (" + result + ") - was processed by "+ Thread.currentThread().getName());
        return result;
    }

}
