package it.sunnyvale.academy.jsenewfeatures.streamapi;

import java.util.List;
import java.util.function.Consumer;

/**
 * FilterExample
 */
public class PeekExample {

    public static void main(String[] args) {

        
        Consumer<SalesTxn> consumer = t -> System.out.println(t);

        List<SalesTxn> txnList = SalesTxn.createTxnList();
        long count = txnList.stream()
            .filter(t -> t.getBuyerName().contains("Mario"))
            .peek(consumer)
            .count();
        System.out.println(String.format("Count: %d",count));
              
    }
}
