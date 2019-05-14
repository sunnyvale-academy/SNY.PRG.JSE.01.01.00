# High-performance modern Java

The Graal name in the GraalVM comes from the Graal compiler. Graal is one compiler to rule them all, meaning that it’s a single implementation of a compiler written as a library which can be used for many different things

We’ll use the HighPerformande.java example, which gives you the top-ten words in a document. It uses modern Java language features like streams and collectors, to test the performance of Graal JIT compiler instead of the C++ one.

Setup the JAVA_HOME and PATH variables

```
$ export JAVA_HOME=/path/to/JDK11/Home # ie: export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.0.3.jdk/Contents/Home
$ export PATH=$JAVA_HOME/bin:$PATH


$ java -version

java version "11.0.3" 2019-04-16 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.3+12-LTS)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.3+12-LTS, mixed mode, sharing)
```

```
$ mkdir -p HighPerformance/target/classes  
$ javac HighPerformance/src/main/java/it/sunnyvale/academy/jsenewfeatures/hp/HighPerformance.java -d HighPerformance/target/classes
```

Let's run with Graal JIT Compiler DISABLED

```
$ time java \
    -cp HighPerformance/target/classes \
    it.sunnyvale.academy.jsenewfeatures.hp.HighPerformance \
    large.txt


3.94s user 
0.41s system 
197% cpu 
2.196 total
```

Now we use Graal JIT compiler to check the performance difference.
We then use -XX:+EnableJVMCI to say that we want to use JVMCI, and -XX:+UseJVMCICompiler to say that we actually want to use it and to install a new JIT compiler.

```
$ time java \
    -XX:+UnlockExperimentalVMOptions \
    -XX:+EnableJVMCI \
    -XX:+UseJVMCICompiler \
    -XX:-TieredCompilation \
    -Djvmci.Compiler=graal \
    -cp HighPerformance/target/classes \
    it.sunnyvale.academy.jsenewfeatures.hp.HighPerformance \
    large.txt




```
