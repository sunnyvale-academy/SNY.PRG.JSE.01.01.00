package it.sunnyvale.academy.jsenewfeatures.streamapi;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * FilterExample
 */
public class CollectingExample {

    public static void main(String[] args) {
       

        // collect result into a list
        Predicate<SalesTxn> filterCriteria1 = t -> t.getBuyerName().contains("Mario");
        List<SalesTxn> txnList = SalesTxn.createTxnList();
        List<SalesTxn> resultList = txnList.stream()
                .filter(filterCriteria1)
                .collect(Collectors.toList());
        for(SalesTxn txn : resultList){
            System.out.println(txn);
        }
        System.out.println(String.format("Result as List: %s",resultList));

        // collect result into a map
        Function<SalesTxn,String> keyMapperFunction =  t -> t.getBuyerName();
        Function<SalesTxn,Double> valueMapperFunction = t -> new Double(t.getTransactionTotal());
        Map<String, Double> resultMap = txnList.stream()
                .filter(filterCriteria1)
                .collect(Collectors.toMap(keyMapperFunction, valueMapperFunction));
        System.out.println(String.format("Result as Map: %s",resultMap));

        // collect result into a string made by buyer names with charachter "," as a delimiter
        String resultString = txnList.stream()
                .map( t -> t.getBuyerName())
                .collect(Collectors.joining(", "));
        System.out.println(String.format("Joining buyer names: %s",resultString));

        // collect result as Transaction Total average
        ToDoubleFunction<SalesTxn> toDoubleFunction = t -> new Double(t.getTransactionTotal());
        double txnTotalAvg = txnList.stream()
        .collect(Collectors.averagingDouble(toDoubleFunction));
        System.out.println(String.format("Averaging Double: %.2f",txnTotalAvg));

        // collect in a Map, grouping by state
        Function<SalesTxn,String> groupingByState =  t -> t.getState();
        Map groupingByStateMap = txnList.stream()
        .collect(Collectors.groupingBy(groupingByState));
        System.out.println(String.format("Grouping by state: %s",groupingByStateMap));

        // collect in a Map, partitioning by Transaction Total (+/- 100)
        Predicate<SalesTxn> partitioningPredicate = t -> t.getTransactionTotal() >= 100;
        Map partitioningByTransactionTotalMap = txnList.stream()
        .collect(Collectors.partitioningBy(partitioningPredicate));
        System.out.println(String.format("Partitioning by Transaction Total +/- 100: %s",partitioningByTransactionTotalMap));

        
    }
}
