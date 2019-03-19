package it.sunnyvale.academy.jsenewfeatures.streamapi;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * FilterExample
 */
public class CollectingExample {

    public static void main(String[] args) {
       
        Predicate<SalesTxn> filterCriteria1 = t -> t.getBuyerName().contains("Mario");

        // collect result into a list
        List<SalesTxn> txnList = SalesTxn.createTxnList();
        List<SalesTxn> resultList = txnList.stream()
                .filter(filterCriteria1)
                .collect(Collectors.toList());

        for(SalesTxn txn : resultList){
            System.out.println(txn);
        }

        // collect result into a map
        Function<SalesTxn,String> keyMapperFunction =  t -> t.getBuyerName();
        Function<SalesTxn,Double> valueMapperFunction = t -> new Double(t.getTransactionTotal());
        Map<String, Double> resultMap = txnList.stream()
                .filter(filterCriteria1)
                .collect(Collectors.toMap(keyMapperFunction, valueMapperFunction));
        System.out.println(resultMap);

        // collect result into a string made by buyer names with charachter "," as a delimiter
        String resultString = txnList.stream()
                .map( t -> t.getBuyerName())
                .collect(Collectors.joining(", "));
        System.out.println(resultString);
        
    }
}
