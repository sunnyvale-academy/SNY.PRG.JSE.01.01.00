package it.sunnyvale.academy.jsenewfeatures.lambdaexpression;

import java.util.List;
import java.util.function.Consumer;

/**
 * ConsumerExample
 */
public class ConsumerExample {

    public static void main(String[] args) {
        List<SalesTxn> tList = SalesTxn.createTxnList();
        SalesTxn first = tList.get(0);

        Consumer<SalesTxn> buyerConsumer = t -> System.out
                .println("Id: " + t.getTxnId() + " Buyer: " + t.getBuyerName());

        System.out.println("== Buyers - Lambda");
        tList.stream().forEach(buyerConsumer);
        //tList.stream().forEach(SalesTxn::printSummary);

        System.out.println("== First Buyer - Method");
        buyerConsumer.accept(first);

    }
}