# GraalVM Native Images

```
$ export JAVA_HOME=/path/to/GraalVM/Home # ie: export JAVA_HOME=/Library/Java/JavaVirtualMachines/graalvm-ee-19.0.0/Contents/Home
$ export PATH=$JAVA_HOME/bin:$PATH

java version "1.8.0_212"
Java(TM) SE Runtime Environment (build 1.8.0_212-b31)
Java HotSpot(TM) GraalVM EE 19.0.0 (build 25.212-b31-jvmci-19-b01, mixed mode)
```

```
$ mkdir -p HelloNativeWorld/target/classes   
$ javac HelloNativeWorld/src/main/java/it/sunnyvale/academy/jsenewfeatures/ni/HelloNativeWorld.java -d HelloNativeWorld/target/classes 
```


```
$ java -cp HelloNativeWorld/target/classes  \
    it.sunnyvale.academy.jsenewfeatures.ni.HelloNativeWorld
```

```
$ gu install native-image
```

```
$ native-image -cp HelloNativeWorld/target/classes  \
    it.sunnyvale.academy.jsenewfeatures.ni.HelloNativeWorld

 classlist: 740.68 ms
 (cap): 1,042.00 ms
 setup: 1,748.77 ms
 (typeflow): 3,350.82 ms
 (objects): 1,258.85 ms
 (features): 0.99 ms
 analysis: 4,702.01 ms
 universe: 288.79 ms
 (parse): 741.91 ms
 (inline): 634.63 ms
 (compile): 6,155.80 ms
 compile: 7,847.51 ms
 image: 1,113.19 ms
 write: 241.73 ms
 [total]: 16,746.19 ms
```


```
$ ls -l 
3.8 M -rwxrwxr-x 1 xxxxx xxxxx 3.8M Dec 12 15:48 hellonativeworld
```


```
$ ./hellonativeworld

Hello Native World!
```

