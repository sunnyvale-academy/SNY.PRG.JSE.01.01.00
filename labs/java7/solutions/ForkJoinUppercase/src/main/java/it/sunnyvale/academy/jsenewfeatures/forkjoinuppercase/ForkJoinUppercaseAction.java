/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sunnyvale.academy.jsenewfeatures.forkjoinuppercase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

/**
 *
 * @author denismaggiorotto
 */
public class ForkJoinUppercaseAction extends RecursiveAction{
    
   private String workload;
   private int threshold;
 
    private static Logger logger = 
      Logger.getAnonymousLogger();
 
    public ForkJoinUppercaseAction(String workload, int threshold) {
        this.workload = workload;
        this.threshold = threshold;
    }
 
    @Override 
    protected void compute() {
        if (workload.length() > threshold) {
            ForkJoinTask.invokeAll(createSubtasks());
        } else {
           processing(workload);
        }
    }
 
    private List<ForkJoinUppercaseAction> createSubtasks() {
        List<ForkJoinUppercaseAction> subtasks = new ArrayList<>();
 
        String partOne = workload.substring(0, workload.length() / 2);
        String partTwo = workload.substring(workload.length() / 2, workload.length());
 
        subtasks.add(new ForkJoinUppercaseAction(partOne, threshold));
        subtasks.add(new ForkJoinUppercaseAction(partTwo, threshold));
 
        return subtasks;
    }
 
    private void processing(String work) {
        String result = work.toUpperCase();
        logger.info("This result - (" + result + ") - was processed by "
          + Thread.currentThread().getName());        
    }
    
}
