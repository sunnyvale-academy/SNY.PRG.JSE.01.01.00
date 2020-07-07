package it.sunnyvale.academy.jsenewfeatures.methodsreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * InstanceMethodReferenceObjectType
 * 
 * This class shows an example of an instance method reference of an object of a particular type
 * 
 *  Instead of using:
 *      (obj, args) -> obj.instanceMethod(args)
 *  Just use:
 *      ObjectType::instanceMethod
 */
public class InstanceMethodReferenceObjectType {



    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 3, 50, 24, 40, 2, 9, 18);

        // Using a lambda expression
        System.out.println("## Using a lambda expression");
        numbers.stream()
            .sorted((a, b) -> Integer.compare(a, b))
            .forEach(t -> System.out.println(t));

        // Using an instance method reference of an object type (ObjectType::method)
        System.out.println("## Using an instance method reference of an object type (ObjectType::method)");
        numbers.stream()
            .sorted(Integer::compare)
            .forEach(System.out::println);
        
    }

   
}