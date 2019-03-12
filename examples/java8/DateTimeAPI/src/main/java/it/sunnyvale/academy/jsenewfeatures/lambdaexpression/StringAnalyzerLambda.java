package it.sunnyvale.academy.jsenewfeatures.lambdaexpression;

/**
 *
 * @author denismaggiorotto
 */
public class StringAnalyzerLambda {

    public static void main(String[] args) {
        System.out.println(StringAnalyzerLambda.analyze(args[0], args[1], (target, searchString)->target.contains(searchString)));
    }

    public static boolean analyze(String target, String searchString, StringAnalyzer analyzer){
        return analyzer.analyze(target, searchString);
    }
}