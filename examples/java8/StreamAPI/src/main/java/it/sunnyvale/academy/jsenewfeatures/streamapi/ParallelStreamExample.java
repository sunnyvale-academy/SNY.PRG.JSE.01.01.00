package it.sunnyvale.academy.jsenewfeatures.streamapi;

import java.util.List;

/**
 * ParallelStreamExample
 */
public class ParallelStreamExample {

    public static void main(String[] args) {
        
        List<SalesTxn> list = SalesTxn.createTxnList();

        double r1 = list.stream()
             .filter(e -> e.getState().equals("CO"))
             .mapToDouble(SalesTxn::getTransactionTotal)
             .sequential()
             .sum();
         
         double r2 = list.stream()
             .filter(e -> e.getState().equals("CO"))
             .mapToDouble(SalesTxn::getTransactionTotal)
             .parallel()
             .sum();

    }
}