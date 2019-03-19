package it.sunnyvale.academy.jsenewfeatures.streamapi;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * FilterExample
 */
public class MapExample {

    public static void main(String[] args) {

        List<SalesTxn> txnList = SalesTxn.createTxnList();

        // Map
        System.out.println("#### Generic mapping ####");
        Function<SalesTxn, String> mapFunction = t -> t.getBuyerName();
        txnList.stream()
                .map(mapFunction)
                .forEach(System.out::println);

        // Map to int
        System.out.println("#### Map to int ####");
        ToIntFunction<SalesTxn> mapToIntFunction = t -> Integer.valueOf(t.getTxnId());
        txnList.stream()
                .mapToInt(mapToIntFunction)
                .forEach(System.out::println);

        // Map to long
        System.out.println("#### Map to long ####");
        ToLongFunction<SalesTxn> mapToLongFunction = t -> Long.valueOf(t.getTxnId());
        txnList.stream()
                .mapToLong(mapToLongFunction)
                .forEach(System.out::println);
        
        // Map to double
        System.out.println("#### Map to double ####");
        ToDoubleFunction<SalesTxn> mapToDoubleFunction = t -> Double.valueOf(t.getTxnId());
        txnList.stream()
                .mapToDouble(mapToDoubleFunction)
                .forEach(System.out::println);


    }
}
