package it.sunnyvale.academy.jsenewfeatures.lambdaexpression;

import java.util.List;
import java.util.function.Function;

/**
 * FunctionExample
 */
public class FunctionExample {

    public static void main(String[] args) {
        List<SalesTxn> tList = SalesTxn.createTxnList();
        Function<SalesTxn, String> buyerFunction = t -> t.getBuyerName();
        SalesTxn first = tList.get(0);
        System.out.println("\n== First Buyer");
        System.out.println(buyerFunction.apply(first));
    }
}