package it.sunnyvale.academy.jsenewfeatures.lambdaexpression;

import java.util.List;
import java.util.function.ToDoubleFunction;

/**
 * ToDoubleFunctionExample
 */
public class ToDoubleFunctionExample {

    public static void main(String[] args) {
        List<SalesTxn> tList = SalesTxn.createTxnList();
        SalesTxn first = tList.get(0);

        ToDoubleFunction<SalesTxn> discountFunction = t -> t.getTransactionTotal();

        System.out.println("\n== Discount");
        System.out.println(discountFunction.applyAsDouble(first));
    }
}