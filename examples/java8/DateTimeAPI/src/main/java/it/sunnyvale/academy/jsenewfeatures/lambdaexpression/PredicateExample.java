package it.sunnyvale.academy.jsenewfeatures.lambdaexpression;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * PredicateExample
 */
public class PredicateExample {

    

    public static void main(String[] args) {
        List<SalesTxn> tList = SalesTxn.createTxnList();

        Predicate<SalesTxn> massSales = t -> t.getState().equals("IT");

        System.out.println("\n== Sales - Stream");
        tList.stream()
            .filter(massSales)
            .forEach(t -> t.printSummary());

        System.out.println("\n== Sales - Method Call");
        for (SalesTxn t : tList) {
            if (massSales.test(t)) {
                t.printSummary();
            }
        }

    }
}