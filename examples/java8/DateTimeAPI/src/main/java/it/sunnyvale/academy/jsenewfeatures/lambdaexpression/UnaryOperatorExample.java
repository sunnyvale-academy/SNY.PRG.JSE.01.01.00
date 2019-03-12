package it.sunnyvale.academy.jsenewfeatures.lambdaexpression;

import java.util.List;
import java.util.function.UnaryOperator;

/**
 * UnaryOperatorExample
 */
public class UnaryOperatorExample {

    public static void main(String[] args) {

        List<SalesTxn> tList = SalesTxn.createTxnList();
        SalesTxn first = tList.get(0);

        UnaryOperator<String> unaryStr = s -> s.toUpperCase();

        System.out.println("== Upper Buyer");
        System.out.println(unaryStr.apply(first.getBuyerName()));

    }
}