package it.sunnyvale.academy.jsenewfeatures.javajit;

class Demo {
    public static void main(String[] args) {
      while (true) {
        workload(14, 2);
      }
    }
  
    private static int workload(int a, int b) {
      return a + b;
    }
  }