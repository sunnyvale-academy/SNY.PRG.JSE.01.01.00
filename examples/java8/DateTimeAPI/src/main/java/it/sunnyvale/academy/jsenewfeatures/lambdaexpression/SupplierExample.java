package it.sunnyvale.academy.jsenewfeatures.lambdaexpression;

import java.util.List;
import java.util.function.Supplier;

/**
 * SupplierExample
 */
public class SupplierExample {

    public static void main(String[] args) {
        List<SalesTxn> tList = SalesTxn.createTxnList();
        Supplier<SalesTxn> txnSupplier = () -> new SalesTxn.Builder()
                                                .txnId(101)
                                                .buyerName("Prova")
                                                .state("IT")
                                                .build();

        tList.add(txnSupplier.get());
        System.out.println("\n== TList");
        tList.stream().forEach(SalesTxn::printSummary);
    }
}