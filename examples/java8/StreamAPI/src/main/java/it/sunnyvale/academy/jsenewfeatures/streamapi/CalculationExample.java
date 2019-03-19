package it.sunnyvale.academy.jsenewfeatures.streamapi;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Predicate;

/**
 * FilterExample
 */
public class CalculationExample {

    public static void main(String[] args) {

        Predicate<SalesTxn> filterCriteria = t -> t.getState().equals("CA");
        List<SalesTxn> txnList = SalesTxn.createTxnList();
        Optional<SalesTxn> optional = null;
        Comparator<SalesTxn> transactionTotalComparator = (t, s) -> {
            if(t.getTransactionTotal() > s.getTransactionTotal()){
                return 1;
            }else if(t.getTransactionTotal() < s.getTransactionTotal()){
                return -1;
            }else{
                return 0;
            }
        };

        // count
        long count = txnList.stream()
                .filter(filterCriteria)
                .count();
        System.out.println(String.format("Count: %d", count));

         // max
        optional = txnList.stream().max(transactionTotalComparator);
        System.out.println(optional);

        // min
        optional = txnList.stream().min(transactionTotalComparator);
        System.out.println(optional);

        // average
        OptionalDouble optionalDouble = txnList.stream()
            .mapToDouble(t -> t.getTransactionTotal())
            .average();
        System.out.println(optionalDouble);

        // sum
        double sum = txnList.stream()
            .mapToDouble(t -> t.getTransactionTotal())
            .sum();
        System.out.println(String.format("Sum: %.2f",sum));

    }
}
