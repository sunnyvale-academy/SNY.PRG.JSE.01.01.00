package it.sunnyvale.academy.jsenewfeatures.methodsreference;

import java.util.Arrays;
import java.util.List;

/**
 * ConstructorMethodReference
 *
 * This class shows an example of a contructor method reference
 * 
 *  Instead of using:
 *      (args) -> new ClassName(args)
 *  Just use:
 *      ClassName::new
 */
public class ConstructorMethodReference {

    class Bicycle {
        String brand;

        public Bicycle(String brand) {
            this.brand = brand;
        }

        public String getBrand() {
            return brand;
        }

        @Override
        public String toString() {
            return this.brand;
        }

    }

    public void execute() {

        List<String> bikeBrands = Arrays.asList("Giant", "Scott", "Trek", "GT");

        // Using lamnda expressions
        bikeBrands.stream()
            .map((b)-> new Bicycle(b))
            .forEach((t)->System.out.println(t));

       
        // Using constructor method references
        bikeBrands.stream()
            .map(Bicycle::new)
            .forEach((t)->System.out.println(t));

    }

    public static void main(String[] args) {
        new ConstructorMethodReference().execute();
    }
}