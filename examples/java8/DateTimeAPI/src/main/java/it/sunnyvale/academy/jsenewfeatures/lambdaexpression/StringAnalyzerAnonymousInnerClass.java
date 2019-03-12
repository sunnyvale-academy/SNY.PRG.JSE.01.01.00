package it.sunnyvale.academy.jsenewfeatures.lambdaexpression;

/**
 *
 * @author denismaggiorotto
 */
public class StringAnalyzerAnonymousInnerClassExample {

    public static void main(String[] args) {
        System.out.println(StringAnalyzerAnonymousInnerClassExample.analyze(args[0], args[1], new StringAnalyzer() {
        
            @Override
            public boolean analyze(String target, String searchStr) {
                return target.contains(searchStr);
            }

        }));
    }

    public static boolean analyze(String target, String searchString, StringAnalyzer analyzer){
        return analyzer.analyze(target, searchString);
    }
}