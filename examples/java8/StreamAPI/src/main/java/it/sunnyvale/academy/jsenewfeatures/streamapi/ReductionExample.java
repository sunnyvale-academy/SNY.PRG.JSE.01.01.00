package it.sunnyvale.academy.jsenewfeatures.streamapi;

import java.util.List;
import java.util.Optional;

/**
 * ReductionExample
 */
public class ReductionExample {

    public static void main(String[] args) {
        
        List<SalesTxn> list = SalesTxn.createTxnList();

        double total = list.stream()
             .filter(e -> e.getState().equals("CA"))
             .mapToDouble(SalesTxn::getTransactionTotal)
             .sum();
        System.out.println(String.format("Using sum(): %.2f",total));

        double reduce = list.stream()
             .filter(e -> e.getState().equals("CA"))
             .mapToDouble(SalesTxn::getTransactionTotal)
             .reduce(0, (sum, element) -> sum + element);
        System.out.println(String.format("Using reduce(): %.2f",reduce));


        Optional<SalesTxn> txn = list.stream()
             .reduce((t1, t2) ->  t1.getTransactionTotal() > t2.getTransactionTotal() ? t1 : t2);
        txn.ifPresent(System.out::println);
    }
}