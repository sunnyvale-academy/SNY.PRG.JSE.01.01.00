package it.sunnyvale.academy.jsenewfeatures.lambdaexpression;

/**
 *
 * @author denismaggiorotto
 */

// @FunctionalInterface annotation prevents this interface to have more than one public abstract method, can be omitted
@FunctionalInterface
public interface StringAnalyzer {
  public boolean analyze(String target, String searchStr);
  
  // Uncomment to get the error "Invalid '@FunctionalInterface' annotation; StringAnalyzer is not a functional interface" at compile time
  // public boolean analyzeMany(String[] target, String searchStr);
}
