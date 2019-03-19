package it.sunnyvale.academy.jsenewfeatures.streamapi;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * FilterExample
 */
public class SearchMethodsExample {

    public static void main(String[] args) {

        Predicate<SalesTxn> findCriteria = null;
        Optional<SalesTxn> optional = null;
       
        // findFirst
        findCriteria = t -> t.getBuyerName().contains("Mario");
        List<SalesTxn> txnList = SalesTxn.createTxnList();
        optional = txnList.stream()
                .filter(findCriteria)
                .findFirst();
        System.out.println("#### findFirst() ####");          
        System.out.println("Optional: " + optional);     
        System.out.println("Optional is present: " +optional.isPresent());    
        
        // findAny
        findCriteria = t -> t.getBuyerName().contains("Denis");
        optional = txnList.stream()
                .filter(findCriteria)
                .findAny();
        System.out.println("#### findAny() ####");          
        System.out.println(optional);     
        System.out.println("Optional is present: " +optional.isPresent());   

        // allMatch
        findCriteria = t -> t.getTransactionTotal() > 0;
        boolean allMatch = txnList.stream()
                .allMatch(findCriteria);
        System.out.println("#### allMatch() ####");          
        System.out.println(allMatch);  
        
        // noneMatch
        findCriteria = t -> t.getBuyerName().contains("Denis");
        boolean noneMatch = txnList.stream()
                .noneMatch(findCriteria);
        System.out.println("#### noneMatch() ####");          
        System.out.println(noneMatch);  

        // anyMatch 
        findCriteria = t -> t.getBuyerName().contains("Larry");
        boolean anyMatch = txnList.stream()
                .anyMatch(findCriteria);
        System.out.println("#### anyMatch() ####");          
        System.out.println(anyMatch);  

        

    }
}
