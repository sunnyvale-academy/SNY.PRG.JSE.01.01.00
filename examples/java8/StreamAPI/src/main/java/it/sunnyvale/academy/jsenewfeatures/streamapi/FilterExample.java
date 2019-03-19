package it.sunnyvale.academy.jsenewfeatures.streamapi;

import java.util.List;
import java.util.function.Predicate;

/**
 * FilterExample
 */
public class FilterExample {

    public static void main(String[] args) {

        Predicate<SalesTxn> filterCriteria1 = t -> t.getState().equals("CA");
        Predicate<SalesTxn> filterCriteria2 = t -> t.getBuyerName().contains("Elon");

        List<SalesTxn> txnList = SalesTxn.createTxnList();
        txnList.stream()
                .filter(filterCriteria1)
                .filter(filterCriteria2)
                .forEach(SalesTxn::printSummary);
    }
}
