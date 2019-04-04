package it.sunnyvale.academy.jsenewfeatures.methodsreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;


/**
 * InstanceMethodReference
 * 
 * This class shows an example of a static method reference
 * 
 *  Instead of using:
 *      (args) -> Class.staticMethod(args)
 *  Just use:
 *      Class::staticMethod
 */

public class StaticMethodReference {
   

      public static void main(String[] args) {
        List<String> messages = Arrays.asList("hello", "baeldung", "readers!");

        // We can call the String.format method:
        
        // Using a lambda expression
        messages.forEach(word -> String.format(word));
        
        // Using a static method reference (Class::staticMethod)
        messages.forEach(String::format); 

      }
}