package it.sunnyvale.academy.jsenewfeatures.lambdaexpression;

/**
 *
 * @author denismaggiorotto
 */
public class StringAnalyzerLambda {


    public static void main(String[] args) {

        int counter = 0;

        // Compact Lambda Expression
        System.out.println(StringAnalyzerLambda.analyze(args[0], args[1], (target, searchString)->target.contains(searchString)));

        // Lambda Expression with parameter type declaration
        System.out.println(StringAnalyzerLambda.analyze(args[0], args[1], (String target, String searchString)->target.contains(searchString)));

        // Multi line Lambda Expression
        System.out.println(StringAnalyzerLambda.analyze(args[0], args[1], (String target, String searchString)-> {
            System.out.println("Don't do this!");
            return target.contains(searchString);
        }));


        System.out.println(StringAnalyzerLambda.analyze(args[0], args[1], (String target, String searchString)-> {
            System.out.println("Don't do this!");
            // Uncomment to get the error "Local variable counter defined in an enclosing scope must be final or effectively final" at compile time
            // counter = counter+1;
            return target.contains(searchString);
        }));

    }

    public static boolean analyze(String target, String searchString, StringAnalyzer analyzer){
        return analyzer.analyze(target, searchString);
    }
}