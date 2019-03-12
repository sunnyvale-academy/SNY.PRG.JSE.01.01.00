package it.sunnyvale.academy.jsenewfeatures.lambdaexpression;

import java.util.List;
import java.util.function.BiPredicate;

/**
 * BiPredicateExample
 */
public class BiPredicateExample {

    public static void main(String[] args) {
        List<SalesTxn> tList = SalesTxn.createTxnList();
        SalesTxn first = tList.get(0);
        String testState = "CA";

        BiPredicate<SalesTxn, String> stateBiPred = (t, s) -> t.getState().equals(s);

        System.out.println("\n== First is CA?");
        System.out.println(stateBiPred.test(first, testState));

    }
}