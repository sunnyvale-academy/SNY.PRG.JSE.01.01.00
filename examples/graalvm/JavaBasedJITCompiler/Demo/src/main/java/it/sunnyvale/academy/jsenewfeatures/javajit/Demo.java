package it.sunnyvale.academy.jsenewfeatures.javajit;

class Demo {
    public static void main(String[] args) {
      long i = 1000000000L;
      while (i > 0) {
        workload(14, 2);
        i--;
      }
    }
  
    private static int workload(int a, int b) {
      return a + b;
    }
  }