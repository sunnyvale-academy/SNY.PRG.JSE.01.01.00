package it.sunnyvale.academy.jsenewfeatures.streamapi;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * FilterExample
 */
public class SortingExample {
 
    public static void main(String[] args) {

        List<SalesTxn> txnList = SalesTxn.createTxnList();

        // sorting with natural SalesTxn ordering (SalesTxn implements Comparable)
        System.out.println("#### Natural SalesTxn ordering (buyer name) ####");
        txnList.stream()
                .sorted()
                .forEach(SalesTxn::printSummary);

        // sorting with a specified ordering
        Comparator<SalesTxn> transactionTotalComparator = (t, s) -> {
            if(t.getTransactionTotal() < s.getTransactionTotal()){
                return 1;
            }else if(t.getTransactionTotal() > s.getTransactionTotal()){
                return -1;
            }else{
                return 0;
            }
        };
        System.out.println("#### SalesTxn ordering by TransactionTotal ####");
        txnList.stream()
                .sorted(transactionTotalComparator)
                .forEach(SalesTxn::printSummary);

        // sorting
        System.out.println("#### SalesTxn ordering by tnx ID (Comparator.comparing) ####");
        Function<SalesTxn, Integer> comparingFunction1 = t -> new Integer(t.getTxnId());
        Function<SalesTxn, Double> comparingFunction2 = t -> new Double(t.getTransactionTotal());
        txnList.stream()
                .sorted(Comparator.comparing(comparingFunction1))
                .forEach(SalesTxn::printSummary);

        System.out.println("#### SalesTxn ordering by tnx ID (Comparator.comparing, thenComparing) ####");
        Function<SalesTxn, String> comparingFunction3 = t -> t.getBuyerName();
        txnList.stream()
                .sorted(Comparator.comparing(comparingFunction2)
                                .thenComparing(comparingFunction3))
                .forEach(SalesTxn::printSummary);

        System.out.println("#### SalesTxn ordering by TransactionTotal (reversed) ####");
        txnList.stream()
                .sorted(transactionTotalComparator.reversed())
                .forEach(SalesTxn::printSummary);


        
    }
}
