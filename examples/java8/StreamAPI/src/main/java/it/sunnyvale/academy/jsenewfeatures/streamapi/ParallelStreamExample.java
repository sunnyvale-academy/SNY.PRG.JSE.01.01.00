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
             // optional, because by default streams are processed sequentally
             .sequential()
             .sum();
        System.out.println("r1="+r1);
         
        double r2 = list.stream()
             .filter(e -> e.getState().equals("CO"))
             .mapToDouble(SalesTxn::getTransactionTotal)
             .parallel()
             .sum();
         System.out.println("r2="+r2);

    }
}